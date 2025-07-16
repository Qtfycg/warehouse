package com.qtfycg.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.admin.domain.Vo.createVo;
import com.qtfycg.admin.domain.entity.hotel;
import com.qtfycg.common.R.R;


public interface hotelService extends IService<hotel> {

    R createHotel(createVo createVo);
}
