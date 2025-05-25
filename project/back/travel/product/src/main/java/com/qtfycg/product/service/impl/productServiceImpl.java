package com.qtfycg.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.common.utils.R.R;
import com.qtfycg.product.Vo.detailVo;
import com.qtfycg.product.Vo.saveVo;
import com.qtfycg.product.Vo.searchVo;
import com.qtfycg.product.entity.product;
import com.qtfycg.product.entity.product_flight;
import com.qtfycg.product.entity.product_hotel;
import com.qtfycg.product.entity.product_train;
import com.qtfycg.product.mapper.productMapper;
import com.qtfycg.product.mapper.product_flightMapper;
import com.qtfycg.product.mapper.product_hotelMapper;
import com.qtfycg.product.mapper.product_trainMapper;
import com.qtfycg.product.service.productService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class productServiceImpl extends ServiceImpl<productMapper, product> implements productService {

    @Resource
    product_trainMapper product_trainMapper;
    @Resource
    product_flightMapper product_flightMapper;
    @Resource
    product_hotelMapper product_hotelMapper;


    /*
    * 新增产品
    * */
    @Override
    public R add(saveVo saveVo) {
        QueryWrapper<product> queryWrapper = new QueryWrapper<>();
        String title = saveVo.getTitle();
        queryWrapper.eq("title", title);
        product existingProduct = this.getOne(queryWrapper);
        if (existingProduct != null){
            return R.error().message("产品已存在");
        }
        product newProduct = getProduct(saveVo);
        this.save(newProduct);
        long productId = newProduct.getId();
        switch (saveVo.getType()) {
            // 火车票
            case 1:
                product_train train = new product_train();
                train.setId(productId);
                train.setTrainNo(saveVo.getTrainNo());
                train.setDepartureStation(saveVo.getDepartureStation());
                train.setArrivalStation(saveVo.getArrivalStation());
                train.setDepartureTime(saveVo.getDepartureTime());
                train.setArrivalTime(saveVo.getArrivalTime());
                product_trainMapper.insert(train);
                break;
            //飞机票
            case 2:
                product_flight flight = new product_flight();
                flight.setId(productId);
                flight.setFlightNo(saveVo.getFlightNo());
                flight.setDepartureAirport(saveVo.getDepartureAirport());
                flight.setArrivalAirport(saveVo.getArrivalAirport());
                flight.setDepartureTime(saveVo.getDepartureTime());
                flight.setArrivalTime(saveVo.getArrivalTime());
                product_flightMapper.insert(flight);
                break;
            //酒店
            case 3:
                product_hotel hotel = new product_hotel();
                hotel.setId(productId);
                hotel.setHotelName(saveVo.getHotelName());
                hotel.setAddress(saveVo.getAddress());
                hotel.setStars(saveVo.getStars());
                hotel.setRoomType(saveVo.getRoomType());
                product_hotelMapper.insert(hotel);
                break;
        }
        return R.ok().message("添加成功").data("product", newProduct);
    }


    /*
    * product newProduct = detailVo(saveVo);
    * */
    private static product getProduct(saveVo saveVo) {
        product newProduct = new product();
        newProduct.setTitle(saveVo.getTitle());
        newProduct.setSubTitle(saveVo.getSubTitle());
        newProduct.setDestination(saveVo.getDestination());
        newProduct.setDeparture(saveVo.getDeparture());
        newProduct.setCoverUrl(saveVo.getCoverUrl());
        newProduct.setPrice(saveVo.getPrice());
        newProduct.setOriginalPrice(saveVo.getOriginalPrice());
        newProduct.setDescription(saveVo.getDescription());
        newProduct.setDetailContent(saveVo.getDetailContent());
        newProduct.setType(saveVo.getType());
        newProduct.setStatus(1);
        return newProduct;
    }


    /*
    * 获取产品详细信息
    * */
    @Override
    public R getDetail(Long id) {
        QueryWrapper<product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        detailVo detailVo = new detailVo();
        product existingProduct = this.getOne(queryWrapper);
        if (existingProduct != null){
            switch (existingProduct.getType()){
                case 1:
                    product_train train = product_trainMapper.selectById(existingProduct.getId());
                    detailVo.setId(train.getId());
                    detailVo.setType(existingProduct.getType());
                    detailVo.setTrainNo(train.getTrainNo());
                    detailVo.setDepartureStation(train.getDepartureStation());
                    detailVo.setArrivalStation(train.getArrivalStation());
                    detailVo.setDepartureTime(train.getDepartureTime());
                    detailVo.setArrivalTime(train.getArrivalTime());
                    return R.ok().message("获取商品详情成功").data("product", detailVo);
                case 2:
                    product_flight flight = product_flightMapper.selectById(existingProduct.getId());
                    detailVo.setId(flight.getId());
                    detailVo.setType(existingProduct.getType());
                    detailVo.setFlightNo(flight.getFlightNo());
                    detailVo.setDepartureAirport(flight.getDepartureAirport());
                    detailVo.setArrivalAirport(flight.getArrivalAirport());
                    detailVo.setDepartureTime(flight.getDepartureTime());
                    detailVo.setArrivalTime(flight.getArrivalTime());
                    return R.ok().message("获取商品详情成功").data("product", detailVo);
                case 3:
                    product_hotel hotel = product_hotelMapper.selectById(existingProduct.getId());
                    detailVo.setId(hotel.getId());
                    detailVo.setType(existingProduct.getType());
                    detailVo.setHotelName(hotel.getHotelName());
                    detailVo.setAddress(hotel.getAddress());
                    detailVo.setStars(hotel.getStars());
                    detailVo.setRoomType(hotel.getRoomType());
                    return R.ok().message("获取商品详情成功").data("product", detailVo);
            }
        }
        return R.error().message("获取商品详情失败");
    }


    /*
    * 搜索产品
    * */
    @Override
    public R searchProducts(searchVo searchVo) {
        String title = searchVo.getTitle();
        String type = searchVo.getType();
        BigDecimal minPrice = searchVo.getMinPrice();
        BigDecimal maxPrice = searchVo.getMaxPrice();

        Page<product> pageParam = new Page<>(searchVo.getPage(), searchVo.getLimit());
        QueryWrapper<product> queryWrapper = new QueryWrapper<>();
        if (title != null && !title.isEmpty()) {
            queryWrapper.and(wrapper -> wrapper
                    .like("title", title)
                    .or().like("type", type)
            );
        }else {
            return R.error().message("搜索关键字不能为空");
        }
        if(type != null && !type.trim().isEmpty()){
            queryWrapper.eq("type", type);
        }
        if (minPrice != null) {
            queryWrapper.ge("price", minPrice);
        }
        if (maxPrice != null) {
            queryWrapper.le("price", maxPrice);
        }
        queryWrapper.eq("status", 1);
        queryWrapper.orderByDesc("price");
        Page<product> resultPage = this.page(pageParam, queryWrapper);
            return R.ok()
                    .message("搜索成功").
                    data("products", resultPage.getRecords())
                    .data("total", resultPage.getTotal());
        }
}




