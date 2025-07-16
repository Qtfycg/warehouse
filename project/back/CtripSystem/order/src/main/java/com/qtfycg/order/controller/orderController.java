package com.qtfycg.order.controller;

import com.qtfycg.order.service.orderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/order")
public class orderController {
    @Resource
    orderService orderService;
    // 订单相关的接口可以在这里定义

    // 例如：创建订单、查询订单、取消订单等

}
