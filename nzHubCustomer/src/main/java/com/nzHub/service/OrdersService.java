package com.nzHub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nzHub.entity.Orders;
import com.nzHub.vo.OrdersVO;

import java.util.List;

/**
 * <p>
 *  service class
 * </p>
 *
 */
public interface OrdersService extends IService<Orders> {
    public List<OrdersVO> findAllByUserId(Integer id);
}
