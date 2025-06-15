package com.nzHub.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nzHub.entity.Cart;
import com.nzHub.entity.Orders;
import com.nzHub.entity.User;
import com.nzHub.exception.nzHubException;
import com.nzHub.result.ResponseEnum;
import com.nzHub.service.CartService;
import com.nzHub.service.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  frontend controller
 * </p>
 *
 */
@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserAddressService userAddressService;

    /**
     * add cart
     * @return
     */
    @GetMapping("/add/{productId}/{price}/{quantity}")
    public String add(
            @PathVariable("productId") Integer productId,
            @PathVariable("price") Float price,
            @PathVariable("quantity") Integer quantity,
            HttpSession session
    ){
        if(productId == null || price == null || quantity == null){
            log.info("[add cart]empty parameter");
            throw new nzHubException(ResponseEnum.PARAMETER_NULL);
        }
        //Check whether the user is already logged in
        User user = (User) session.getAttribute("user");
        if(user == null){
            log.info("[add cart]You have not log in, please login first");
            throw new nzHubException(ResponseEnum.NOT_LOGIN);
        }
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setCost(price * quantity);
        Boolean add = this.cartService.add(cart);
        if(!add){
            log.info("[add cart]fail to add cart");
            throw new nzHubException(ResponseEnum.CART_ADD_ERROR);
        }
        return "redirect:/cart/get";
    }

    /**
     * view the cart
     * @param session
     * @return
     */
    @GetMapping("/get")
    public ModelAndView get(HttpSession session){
        //Check whether the user is already logged in
        User user = (User) session.getAttribute("user");
        if(user == null){
            log.info("[add cart]You have not log in, please login first");
            throw new nzHubException(ResponseEnum.NOT_LOGIN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement1");
        modelAndView.addObject("cartList", this.cartService.findVOListByUserId(user.getId()));
        return modelAndView;
    }

    /**
     * update cart
     * @return
     */
    @PostMapping("/update/{id}/{quantity}/{cost}")
    @ResponseBody
    public String update(
            @PathVariable("id") Integer id,
            @PathVariable("quantity") Integer quantity,
            @PathVariable("cost") Float cost,
            HttpSession session
    ){
        if(id == null || quantity == null || cost == null){
            log.info("[add cart]empty parameter");
            throw new nzHubException(ResponseEnum.PARAMETER_NULL);
        }
        //Check whether the user is already logged in
        User user = (User) session.getAttribute("user");
        if(user == null){
            log.info("[add cart]You have not log in, please login first");
            throw new nzHubException(ResponseEnum.NOT_LOGIN);
        }
        if(this.cartService.update(id, quantity, cost)) return "success";
        return "fail";
    }

    /**
     * delete cart
     * @param id
     * @param session
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id,HttpSession session){
        if(id == null){
            log.info("[update cart]empty parameter");
            throw new nzHubException(ResponseEnum.PARAMETER_NULL);
        }
        //Check whether the user is already logged in
        User user = (User) session.getAttribute("user");
        if(user == null){
            log.info("[update cart]You have not log in, please login first");
            throw new nzHubException(ResponseEnum.NOT_LOGIN);
        }
        Boolean delete = this.cartService.delete(id);
        if(delete) return "redirect:/cart/get";
        return null;
    }

    /**
     * confirm order
     * @param session
     * @return
     */
    @GetMapping("/confirm")
    public ModelAndView confirm(HttpSession session){
        //Check whether the user is already logged in
        User user = (User) session.getAttribute("user");
        if(user == null){
            log.info("[update cart]You have not log in, please login first");
            throw new nzHubException(ResponseEnum.NOT_LOGIN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement2");
        modelAndView.addObject("cartList", this.cartService.findVOListByUserId(user.getId()));
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", user.getId());
        modelAndView.addObject("addressList", this.userAddressService.list(queryWrapper));
        return modelAndView;
    }

    /**
     * confirm order
     * @param userAddress
     * @param session
     * @return
     */
    @PostMapping("/commit")
    public ModelAndView commit(
            String userAddress,
            String address,
            String remark,
            HttpSession session){
        if(userAddress == null || address == null || remark == null){
            log.info("[update cart]empty parameter");
            throw new nzHubException(ResponseEnum.PARAMETER_NULL);
        }
        //Check whether the user is already logged in
        User user = (User) session.getAttribute("user");
        if(user == null){
            log.info("[update cart]You have not log in, please login first");
            throw new nzHubException(ResponseEnum.NOT_LOGIN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement3");
        Orders orders = this.cartService.commit(userAddress, address, remark, user);
        if(orders != null){
            modelAndView.addObject("orders", orders);
            modelAndView.addObject("cartList", this.cartService.findVOListByUserId(user.getId()));
            return modelAndView;
        }
        return null;
    }
}

