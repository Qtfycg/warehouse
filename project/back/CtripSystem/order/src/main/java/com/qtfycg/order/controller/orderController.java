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


}
