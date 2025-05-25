package com.qtfycg.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.common.utils.R.R;
import com.qtfycg.product.Vo.saveVo;
import com.qtfycg.product.Vo.searchVo;
import com.qtfycg.product.entity.product;


public interface productService extends IService<product> {
    R add(saveVo saveVo);
    R getDetail(Long id);
    R searchProducts(searchVo searchVo);

}
