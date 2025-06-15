package com.nzHub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nzHub.entity.OrderDetail;
import com.nzHub.mapper.OrderDetailMapper;
import com.nzHub.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *   service implement class
 * </p>
 *
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
