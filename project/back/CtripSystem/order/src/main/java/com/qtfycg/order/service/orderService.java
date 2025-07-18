package com.qtfycg.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.common.R.R;
import com.qtfycg.order.domain.Vo.createVo;
import com.qtfycg.order.domain.entity.order;


public interface orderService extends IService<order> {
    R createOrder(createVo createVo);

}
