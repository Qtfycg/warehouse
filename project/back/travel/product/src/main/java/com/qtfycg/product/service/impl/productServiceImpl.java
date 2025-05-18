package com.qtfycg.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.product.Vo.saveProduct;
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


@Service
public class productServiceImpl extends ServiceImpl<productMapper, product> implements productService {

    @Resource
    product_trainMapper product_trainMapper;
    @Resource
    product_flightMapper product_flightMapper;
    @Resource
    product_hotelMapper product_hotelMapper;


    @Override
    public boolean add(saveProduct saveProduct) {
        QueryWrapper<product> queryWrapper = new QueryWrapper<>();
        String title = saveProduct.getTitle();
        queryWrapper.eq("title", title);
        product existingProduct = this.getOne(queryWrapper);
        if (existingProduct != null){
            return false;
        }
        product newProduct = getProduct(saveProduct);
        this.save(newProduct);
        long productId = newProduct.getId();
        switch (saveProduct.getType()) {
            // 火车票
            case 1:
                product_train train = new product_train();
                train.setId(productId);
                train.setTrainNo(saveProduct.getTrainNo());
                train.setDepartureStation(saveProduct.getDepartureStation());
                train.setArrivalStation(saveProduct.getArrivalStation());
                train.setDepartureTime(saveProduct.getDepartureTime());
                train.setArrivalTime(saveProduct.getArrivalTime());
                product_trainMapper.insert(train);
                break;
            //飞机票
            case 2:
                product_flight flight = new product_flight();
                flight.setId(productId);
                flight.setFlightNo(saveProduct.getFlightNo());
                flight.setDepartureAirport(saveProduct.getDepartureAirport());
                flight.setArrivalAirport(saveProduct.getArrivalAirport());
                flight.setDepartureTime(saveProduct.getDepartureTime());
                flight.setArrivalTime(saveProduct.getArrivalTime());
                product_flightMapper.insert(flight);
                break;
            //酒店
            case 3:
                product_hotel hotel = new product_hotel();
                hotel.setId(productId);
                hotel.setHotelName(saveProduct.getHotelName());
                hotel.setAddress(saveProduct.getAddress());
                hotel.setStars(saveProduct.getStars());
                hotel.setRoomType(saveProduct.getRoomType());
                product_hotelMapper.insert(hotel);
                break;
        }
        return true;
    }


    /*
    * product newProduct = getProduct(saveProduct);
    * */
    private static product getProduct(saveProduct saveProduct) {
        product newProduct = new product();
        newProduct.setTitle(saveProduct.getTitle());
        newProduct.setSubTitle(saveProduct.getSubTitle());
        newProduct.setDestination(saveProduct.getDestination());
        newProduct.setDeparture(saveProduct.getDeparture());
        newProduct.setCoverUrl(saveProduct.getCoverUrl());
        newProduct.setPrice(saveProduct.getPrice());
        newProduct.setOriginalPrice(saveProduct.getOriginalPrice());
        newProduct.setDescription(saveProduct.getDescription());
        newProduct.setDetailContent(saveProduct.getDetailContent());
        newProduct.setType(saveProduct.getType());
        newProduct.setStatus(1);
        return newProduct;
    }


    @Override
    public saveProduct getDetail(saveProduct saveProduct) {
        return null;
    }

}




