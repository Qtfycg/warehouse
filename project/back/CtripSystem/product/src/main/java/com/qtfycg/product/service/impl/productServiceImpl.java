package com.qtfycg.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.product.domain.entity.product;
import com.qtfycg.product.service.productService;
import com.qtfycg.product.mapper.productMapper;
import org.springframework.stereotype.Service;


@Service
public class productServiceImpl extends ServiceImpl<productMapper, product>
    implements productService{

}




