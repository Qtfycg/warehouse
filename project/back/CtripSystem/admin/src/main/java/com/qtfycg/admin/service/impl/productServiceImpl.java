package com.qtfycg.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.admin.domain.Vo.createVo;
import com.qtfycg.admin.domain.entity.product;
import com.qtfycg.admin.domain.entity.user;
import com.qtfycg.admin.mapper.adminMapper;
import com.qtfycg.admin.mapper.productMapper;
import com.qtfycg.admin.service.productService;
import com.qtfycg.common.R.R;
import com.qtfycg.common.SnowflakeId.SnowflakeIdGenerator;
import com.qtfycg.common.annotation.Login;
import com.qtfycg.common.annotation.context.loginHolder;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class productServiceImpl extends ServiceImpl<productMapper, product> implements productService {

    @Resource
    adminMapper adminMapper;


    @Login
    @Override
    public R createProduct(createVo createVo) {
        Long userId = loginHolder.getUserId();
        user userInfo = adminMapper.selectById(userId);
        if(userInfo == null || userInfo.getRole() == 1) {
            return R.error()
                    .code(700)
                    .message("用户权限不足");
        }else {
            product product = getProduct(createVo);
            boolean isSaved = this.save(product);
            if (isSaved) {
                return R.ok()
                        .code(600)
                        .message("商品创建成功")
                        .data("product",product);
            } else {
                return R.error()
                        .code(700)
                        .message("商品创建失败");
            }
        }
    }
    /*
    * product product = getProduct(createVo);
    * */
    private product getProduct(createVo createVo) {
        product product = new product();
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        long id = idGenerator.nextId();
        product.setId(id);
        product.setName(createVo.getName());
        product.setPrice(createVo.getPrice());
        product.setStock(createVo.getStock());
        product.setDescription(createVo.getDescription());
        product.setImageList(createVo.getImageList());
        product.setType(createVo.getType());
        product.setStatus(1);
        return product;
    }
}




