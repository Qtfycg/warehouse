package com.qtfycg.product.service;

import com.qtfycg.common.R.R;
import com.qtfycg.product.domain.entity.product;
import com.baomidou.mybatisplus.extension.service.IService;


public interface productService extends IService<product> {
    R getList(Integer page, Integer size, String keyword);
    R getDetail(Long id);

}
