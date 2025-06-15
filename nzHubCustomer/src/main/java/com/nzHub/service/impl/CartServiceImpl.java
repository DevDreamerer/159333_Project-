package com.nzHub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nzHub.entity.*;
import com.nzHub.exception.nzHubException;
import com.nzHub.mapper.*;
import com.nzHub.result.ResponseEnum;
import com.nzHub.service.CartService;
import com.nzHub.vo.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  service implement class
 * </p>
 *
 */
@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    @Transactional
    public Boolean add(Cart cart) {
        int insert = this.cartMapper.insert(cart);
        if(insert != 1){
            throw new nzHubException(ResponseEnum.CART_ADD_ERROR);
        }
        Integer stock = this.productMapper.getStockById(cart.getProductId());
        if(stock == null){
            log.info("This product not exist");
            throw new nzHubException(ResponseEnum.PRODUCT_NOT_EXISTS);
        }
        if(stock == 0){
            log.info("The stock of this product is not sufficient");
            throw new nzHubException(ResponseEnum.PRODUCT_STOCK_ERROR);
        }
        Integer newStock = stock - cart.getQuantity();
        if(newStock < 0){
            log.info("The stock of this product is not sufficient");
            throw new nzHubException(ResponseEnum.PRODUCT_STOCK_ERROR);
        }
        this.productMapper.updateStockById(cart.getProductId(), newStock);
        return true;
    }

    @Override
    public List<CartVO> findVOListByUserId(Integer userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Cart> cartList = this.cartMapper.selectList(queryWrapper);
        List<CartVO> cartVOList = new ArrayList<>();
        for (Cart cart : cartList) {
            Product product = this.productMapper.selectById(cart.getProductId());
            CartVO cartVO = new CartVO();
            BeanUtils.copyProperties(product, cartVO);
            BeanUtils.copyProperties(cart, cartVO);
            cartVOList.add(cartVO);
        }
        return cartVOList;
    }

    @Override
    @Transactional
    public Boolean update(Integer id, Integer quantity, Float cost) {
        //update stock
        Cart cart = this.cartMapper.selectById(id);
        Integer oldQuantity = cart.getQuantity();
        if(quantity.equals(oldQuantity)){
            log.info("[update cart]wrong parameter");
            throw new nzHubException(ResponseEnum.CART_UPDATE_PARAMETER_ERROR);
        }
        //query product stock
        Integer stock = this.productMapper.getStockById(cart.getProductId());
        Integer newStock = stock - (quantity - oldQuantity);
        if(newStock < 0){
            log.info("[update cart]wrong product stock");
            throw new nzHubException(ResponseEnum.PRODUCT_STOCK_ERROR);
        }
        Integer integer = this.productMapper.updateStockById(cart.getProductId(), newStock);
        if(integer != 1){
            log.info("[update cart]fail to update stock");
            throw new nzHubException(ResponseEnum.CART_UPDATE_STOCK_ERROR);
        }
        //update data
        int update = this.cartMapper.update(id, quantity, cost);
        if(update != 1){
            log.info("[update cart]fail to update");
            throw new nzHubException(ResponseEnum.CART_UPDATE_ERROR);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        //update product stock
        Cart cart = this.cartMapper.selectById(id);
        Integer stock = this.productMapper.getStockById(cart.getProductId());
        Integer newStock = stock + cart.getQuantity();
        Integer integer = this.productMapper.updateStockById(cart.getProductId(), newStock);
        if(integer != 1){
            log.info("[delete cart]fail to delete stock");
            throw new nzHubException(ResponseEnum.CART_UPDATE_STOCK_ERROR);
        }
        //delete cart data
        int i = this.cartMapper.deleteById(id);
        if(i != 1){
            log.info("[delete cart]fail to delete");
            throw new nzHubException(ResponseEnum.CART_REMOVE_ERROR);
        }
        return true;
    }

    @Override
    @Transactional
    public Orders commit(String userAddress, String address, String remark, User user) {
        //handle address
        if(!userAddress.equals("newAddress")){
            address = userAddress;
        }else{
            int i = this.userAddressMapper.setDefault();
            if(i == 0){
                log.info("[confirm order]fail to modify default address");
                throw new nzHubException(ResponseEnum.USER_ADDRESS_SET_DEFAULT_ERROR);
            }
            //save new address into database
            UserAddress userAddress1 = new UserAddress();
            userAddress1.setIsdefault(1);
            userAddress1.setUserId(user.getId());
            userAddress1.setRemark(remark);
            userAddress1.setAddress(address);
            int insert = this.userAddressMapper.insert(userAddress1);
            if(insert == 0){
                log.info("[confirm order]fail to add new address");
                throw new nzHubException(ResponseEnum.USER_ADDRESS_ADD_ERROR);
            }
        }
        //create order main form
        Orders orders = new Orders();
        orders.setUserId(user.getId());
        orders.setLoginName(user.getLoginName());
        orders.setUserAddress(address);
        orders.setCost(this.cartMapper.getCostByUserId(user.getId()));
        String seriaNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<32;i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber =  result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        orders.setSerialnumber(seriaNumber);
        int insert = this.ordersMapper.insert(orders);
        if(insert != 1){
            log.info("[confirm order]fail to create order form");
            throw new nzHubException(ResponseEnum.ORDERS_CREATE_ERROR);
        }
        //create order subform
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        List<Cart> carts = this.cartMapper.selectList(queryWrapper);
        for (Cart cart : carts) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart, orderDetail);
            orderDetail.setOrderId(orders.getId());
            int insert1 = this.orderDetailMapper.insert(orderDetail);
            if(insert1 == 0){
                log.info("[confirm order]fail to create");
                throw new nzHubException(ResponseEnum.ORDER_DETAIL_CREATE_ERROR);
            }
        }
        //clean the cart
        QueryWrapper<Cart> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id", user.getId());
        int delete = this.cartMapper.delete(queryWrapper1);
        if(delete == 0){
            log.info("[confirm order]fail to clean the cart");
            throw new nzHubException(ResponseEnum.CART_REMOVE_ERROR);
        }
        return orders;
    }
}
