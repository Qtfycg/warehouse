package com.qtfycg.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.common.R.R;
import com.qtfycg.common.SnowflakeId.SnowflakeIdGenerator;
import com.qtfycg.common.annotation.Login;
import com.qtfycg.common.annotation.context.loginHolder;
import com.qtfycg.order.domain.Vo.createVo;
import com.qtfycg.order.domain.entity.order;
import com.qtfycg.order.feign.productFeign;
import com.qtfycg.order.feign.userFeign;
import com.qtfycg.order.mapper.orderMapper;
import com.qtfycg.order.service.orderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class orderServiceImpl extends ServiceImpl<orderMapper, order> implements orderService {

    @Resource
    productFeign productFeign;
    @Resource
    userFeign userFeign;


    @Login
    @Override
    public R createOrder(createVo createVo) {
        Long userId = loginHolder.getUserId();
        if (userId == null) {
            return R.error()
                    .code(401)
                    .message("用户未登录");
        }else {
            SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1,5);
            Long orderId = idGenerator.nextId();
            order newOrder = new order();
            newOrder.setId(orderId);
            newOrder.setUserId(userId);
            newOrder.setProductId(createVo.getProductId());
            newOrder.setQuantity(createVo.getQuantity());
            R productResult = productFeign.getDetail(createVo.getProductId());
            if (productResult.getCode() != 600) {
                return R.error()
                        .code(700)
                        .message("商品信息获取失败");
            }
            BigDecimal productPrice = new BigDecimal(productResult.getData().get("price").toString());
            BigDecimal totalAmount = productPrice.multiply(new BigDecimal(createVo.getQuantity()));
            newOrder.setAmount(totalAmount);
            newOrder.setStatus(0);
            R userResult = userFeign.getInfo(userId);
            if( userResult.getCode() != 200) {
                return R.error()
                        .code(300)
                        .message("用户信息获取失败");
            }
            newOrder.setName(userResult.getData().get("username").toString());
            newOrder.setPhone(userResult.getData().get("phone").toString());
            newOrder.setAddress(userResult.getData().get("address").toString());
            newOrder.setRemark(createVo.getRemark());
            newOrder.setCreateTime(new java.util.Date());
            this.save(newOrder);
            return R.ok()
                    .code(800)
                    .message("订单创建成功")
                    .data("order", newOrder);
        }

    }
}




