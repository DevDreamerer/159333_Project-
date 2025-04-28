package com.nzHub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nzHub.entity.Cart;
import com.nzHub.entity.Orders;
import com.nzHub.entity.User;
import com.nzHub.vo.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2021-11-22
 */
public interface CartService extends IService<Cart> {
    public Boolean add(Cart cart);
    public List<CartVO> findVOListByUserId(Integer userId);
    public Boolean update(Integer id,Integer quantity,Float cost);
    public Boolean delete(Integer id);
    public Orders commit(String userAddress, String address, String remark, User user);
}
