package com.nzHub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nzHub.entity.OrderDetail;
import com.nzHub.mapper.OrderDetailMapper;
import com.nzHub.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-11-22
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
