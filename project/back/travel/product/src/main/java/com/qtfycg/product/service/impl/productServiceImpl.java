package com.qtfycg.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.product.Vo.saveProduct;
import com.qtfycg.product.entity.product;
import com.qtfycg.product.mapper.productMapper;
import com.qtfycg.product.service.productService;
import org.springframework.stereotype.Service;


@Service
public class productServiceImpl extends ServiceImpl<productMapper, product>
    implements productService {

    @Override
    public boolean add(saveProduct saveProduct) {
        QueryWrapper<product> queryWrapper = new QueryWrapper<>();
        String name = saveProduct.getTitle();
        queryWrapper.eq("title", name);
        product existingProduct = this.getOne(queryWrapper);
        if (existingProduct != null){
            return false;
        }
        product newProduct = new product();
        newProduct.setTitle(saveProduct.getTitle());
        newProduct.setDestination(saveProduct.getDestination());
        newProduct.setDeparture(saveProduct.getDeparture());
        newProduct.setPrice(saveProduct.getPrice());
        newProduct.setDays(saveProduct.getDays());
        newProduct.setDescription(saveProduct.getDescription());
        newProduct.setDetailContent(saveProduct.getDetailContent());
        newProduct.setCoverUrl(saveProduct.getCoverUrl());
        newProduct.setType(saveProduct.getType());
        return this.save(newProduct);
    }
}




