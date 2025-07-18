package com.qtfycg.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.common.R.R;
import com.qtfycg.common.SnowflakeId.SnowflakeIdGenerator;
import com.qtfycg.common.annotation.Login;
import com.qtfycg.common.annotation.context.loginHolder;
import com.qtfycg.order.domain.Vo.createVo;
import com.qtfycg.order.domain.entity.order;
import com.qtfycg.order.mapper.orderMapper;
import com.qtfycg.order.service.orderService;
import org.springframework.stereotype.Service;


@Service
public class orderServiceImpl extends ServiceImpl<orderMapper, order> implements orderService {

    @Login
    @Override
    public R createOrder(createVo createVo) {
        Long userId = loginHolder.getUserId();
        if (userId == null) {
            return R.error().code(401).message("用户未登录");
        }else {
            SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1,5);
            Long orderId = idGenerator.nextId();
            createVo.setId(orderId);
            createVo.setUserId(userId);


            return R.ok()
                    .code(800)
                    .message("订单创建成功")
                    .data("userId", userId);
        }

    }
}




