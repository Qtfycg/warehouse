package com.qtfycg.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.admin.domain.Vo.createVo;
import com.qtfycg.admin.domain.entity.flight;
import com.qtfycg.admin.domain.entity.product;
import com.qtfycg.admin.mapper.flightMapper;
import com.qtfycg.admin.service.flightService;
import com.qtfycg.admin.service.productService;
import com.qtfycg.common.R.R;
import com.qtfycg.common.SnowflakeId.SnowflakeIdGenerator;
import com.qtfycg.common.annotation.Login;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class flightServiceImpl extends ServiceImpl<flightMapper, flight> implements flightService {

    @Resource
    productService productService;

    @Login
    @Override
    public R createFlight(createVo createVo) {
        QueryWrapper<flight> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flights", createVo.getFlights());
        flight existingFlight = this.getOne(queryWrapper);
        if (existingFlight != null) {
            return R.error()
                    .code(701)
                    .message("航班已存在");
        }else {
            flight flight = new flight();
            SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
            long id = idGenerator.nextId();
            flight.setId(id);
            flight.setFlights(createVo.getFlights());
            flight.setStart(createVo.getStart());
            flight.setEndpoint(createVo.getEndpoint());
            flight.setPrice(createVo.getPrice());
            flight.setDeparturetime(createVo.getDeparturetime());
            flight.setArrivetime(createVo.getArrivetime());
            flight.setStock(createVo.getStock());


            product product = new product();
            product.setId(id);
            product.setStatus(1);
            product.setStock(createVo.getStock());
            product.setName(createVo.getName());
            product.setType(3);
            product.setDescription(createVo.getDescription());
            this.save(flight);
            productService.saveProduct(product);
            return R.ok()
                    .code(200)
                    .data("data", flight)
                    .message("航班创建成功");
        }
    }
}




