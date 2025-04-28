package com.nzHub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nzHub.entity.Orders;
import com.nzHub.vo.OrdersVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2021-11-22
 */
public interface OrdersService extends IService<Orders> {
    public List<OrdersVO> findAllByUserId(Integer id);
}
