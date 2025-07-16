package com.qtfycg.admin.controller;

import com.qtfycg.admin.domain.Vo.createVo;
import com.qtfycg.admin.service.hotelService;
import com.qtfycg.common.R.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/product/hotel")
public class hotelController {
    @Resource
    hotelService hotelService;

    @PostMapping("/create")
    public R createFlight(@RequestBody createVo createVo) {
        return hotelService.createHotel(createVo);
    }
}
