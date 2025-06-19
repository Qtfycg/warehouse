package com.qtfycg.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.admin.domain.Vo.createProductVo;
import com.qtfycg.admin.domain.entity.product;
import com.qtfycg.common.R.R;


public interface productService extends IService<product> {
    R createProduct(createProductVo createProductVo);
    R updateProduct(createProductVo createProductVo);
    R updateProductStatus(Long id, Integer status);
    R getProductInfo(Long id);
}
