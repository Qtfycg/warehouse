package com.qtfycg.order.controller;

import com.qtfycg.common.R.R;
import com.qtfycg.order.domain.Vo.createVo;
import com.qtfycg.order.service.orderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class orderController {
    @Resource
    orderService orderService;

    @PostMapping("/create")
    public R createOrder(@RequestBody createVo createVo) {
        return orderService.createOrder(createVo);
    }


}
