package com.qtfycg.admin.controller;

import com.qtfycg.admin.domain.Vo.createVo;
import com.qtfycg.admin.service.travelService;
import com.qtfycg.common.R.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travel")
public class travelController {
    @Resource
    travelService travelService;

    @PostMapping("/create")
    public R createFlight(@RequestBody createVo createVo) {
        return travelService.createTravel(createVo);
    }
}
