package com.qtfycg.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.admin.domain.Vo.createVo;
import com.qtfycg.admin.domain.entity.travel;
import com.qtfycg.common.R.R;


public interface travelService extends IService<travel> {
    R createTravel(createVo createVo);

}
