package com.qtfycg.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.order.domain.entity.order;
import com.qtfycg.order.mapper.orderMapper;
import com.qtfycg.order.service.orderService;
import org.springframework.stereotype.Service;


@Service
public class orderServiceImpl extends ServiceImpl<orderMapper, order>
    implements orderService {

}




