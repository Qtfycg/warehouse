package com.qtfycg.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.admin.domain.entity.product;
import com.qtfycg.common.R.R;


public interface productService extends IService<product> {
    R updateProductStatus(Long id);
    R getProductInfo(Long id);
    void saveProduct(product product);
}
