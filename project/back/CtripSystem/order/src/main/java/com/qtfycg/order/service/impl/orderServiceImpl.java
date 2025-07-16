package com.qtfycg.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.common.R.R;
import com.qtfycg.common.annotation.Login;
import com.qtfycg.common.annotation.context.loginHolder;
import com.qtfycg.order.domain.entity.order;
import com.qtfycg.order.mapper.orderMapper;
import com.qtfycg.order.service.orderService;
import org.springframework.stereotype.Service;


@Service
public class orderServiceImpl extends ServiceImpl<orderMapper, order> implements orderService {

    @Login
    @Override
    public R createOrder() {
        Long userId = loginHolder.getUserId();
        if (userId == null) {
            return R.error().code(401).message("用户未登录");
        }
        // 这里可以添加创建订单的逻辑
        return R.ok().message("订单创建成功").data("userId", userId);
    }
}




