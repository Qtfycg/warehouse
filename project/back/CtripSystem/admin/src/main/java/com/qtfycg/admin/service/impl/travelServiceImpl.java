package com.qtfycg.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.admin.domain.Vo.createVo;
import com.qtfycg.admin.domain.entity.product;
import com.qtfycg.admin.domain.entity.travel;
import com.qtfycg.admin.mapper.travelMapper;
import com.qtfycg.admin.service.productService;
import com.qtfycg.admin.service.travelService;
import com.qtfycg.common.R.R;
import com.qtfycg.common.SnowflakeId.SnowflakeIdGenerator;
import com.qtfycg.common.annotation.Login;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class travelServiceImpl extends ServiceImpl<travelMapper, travel>
    implements travelService {

    @Resource
    productService productService;


    @Login
    @Override
    public R createTravel(createVo createVo) {
        QueryWrapper<travel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("trips", createVo.getTrips());
        travel existingTravel = this.getOne(queryWrapper);
        if (existingTravel != null) {
            return R.error()
                    .code(701)
                    .message("该线路已存在，请勿重复添加");
        }else {
            travel travel = new travel();
            SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 2);
            Long id = idGenerator.nextId();
            travel.setId(id);
            travel.setTrips(createVo.getTrips());
            travel.setPrice(createVo.getPrice());
            travel.setStart(createVo.getStart());
            travel.setEndpoint(createVo.getEndpoint());
            travel.setDeparturetime(createVo.getDeparturetime());
            travel.setArrivetime(createVo.getArrivetime());
            travel.setPathway(createVo.getPathway());
            travel.setStock(createVo.getStock());
            product product = new product();
            product.setId(id);
            product.setName(createVo.getName());
            product.setType(2);
            product.setStatus(1);
            product.setDescription(createVo.getDescription());
            product.setStock(createVo.getStock());
            this.save(travel);
            productService.save(product);
            return R.ok()
                    .code(200)
                    .data("data", travel)
                    .message("旅游线路添加成功");
        }
    }
}




