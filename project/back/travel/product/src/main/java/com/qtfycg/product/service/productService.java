package com.qtfycg.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.product.Vo.saveProduct;
import com.qtfycg.product.entity.product;


public interface productService extends IService<product> {
    boolean add(saveProduct saveProduct);
    saveProduct getDetail(saveProduct saveProduct);

}
