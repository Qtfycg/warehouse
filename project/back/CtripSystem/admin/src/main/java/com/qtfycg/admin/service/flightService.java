package com.qtfycg.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.admin.domain.entity.flight;
import com.qtfycg.common.R.R;
import com.qtfycg.admin.domain.Vo.createVo;


public interface flightService extends IService<flight> {
    R createFlight(createVo createVo);

}
