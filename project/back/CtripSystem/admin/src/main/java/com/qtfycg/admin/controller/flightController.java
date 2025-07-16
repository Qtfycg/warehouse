package com.qtfycg.admin.controller;

import com.qtfycg.admin.domain.Vo.createVo;
import com.qtfycg.admin.service.flightService;
import com.qtfycg.common.R.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/product/flight")
public class flightController {

    @Resource
    flightService flightService;
    /*新增机票*/
    @PostMapping("/create")
    public R createFlight(@RequestBody createVo createVo) {
        return flightService.createFlight(createVo);
    }
}
