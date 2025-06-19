package com.qtfycg.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.admin.domain.Vo.createProductVo;
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

    /*
    * 新增产品
    * */
    @Login
    @Override
    public R createProduct(createProductVo createProductVo) {
        Long userId = loginHolder.getUserId();
        user userInfo = adminMapper.selectById(userId);
        if(userInfo == null || userInfo.getRole() == 1) {
            return R.error()
                    .code(501)
                    .message("用户权限不足");
        }else {
            product product = getProduct(createProductVo);
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
    * 新增产品时，对象转换
    * product product = getProduct(createProductVo);
    * */
    private product getProduct(createProductVo createProductVo) {
        product product = new product();
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        long id = idGenerator.nextId();
        product.setId(id);
        product.setName(createProductVo.getName());
        product.setPrice(createProductVo.getPrice());
        product.setStock(createProductVo.getStock());
        product.setDescription(createProductVo.getDescription());
        product.setImageList(createProductVo.getImageList());
        product.setType(createProductVo.getType());
        product.setStatus(1);
        return product;
    }

    /*
    * 修改产品
    * */
    @Login
    @Override
    public R updateProduct(createProductVo createProductVo) {
        Long userId = loginHolder.getUserId();
        user userInfo = adminMapper.selectById(userId);
        if(userInfo == null || userInfo.getRole() == 1) {
            return R.error()
                    .code(501)
                    .message("用户权限不足");
        }else {
            product product = getProduct(createProductVo);
            QueryWrapper<product> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", product.getId());
            boolean isUpdated = this.update(product, queryWrapper);
            if (isUpdated) {
                return R.ok()
                        .code(600)
                        .message("商品修改成功")
                        .data("product", product);
            } else {
                return R.error()
                        .code(701)
                        .message("商品修改失败");
            }
        }
    }

    /*
     * 产品上下架
     * */
    @Login
    @Override
    public R updateProductStatus(Long id, Integer status) {
    Long userId = loginHolder.getUserId();
    user userInfo = adminMapper.selectById(userId);
    if(userInfo == null || userInfo.getRole() == 1) {
        return R.error()
                .code(501)
                .message("用户权限不足");
    } else {
        product product = this.getById(id);
        if (product == null) {
            return R.error()
                    .code(702)
                    .message("商品不存在");
        }
        product.setStatus(status);
        boolean isUpdated = this.updateById(product);
        if (isUpdated) {
            return R.ok()
                    .code(600)
                    .message("商品状态更新成功")
                    .data("product", product);
        } else {
            return R.error()
                    .code(703)
                    .message("商品状态更新失败");
        }
    }
    }

    /*
     * 查询产品信息
     * */
    @Login
    @Override
    public R getProductInfo(Long id) {
        Long userId = loginHolder.getUserId();
        user userInfo = adminMapper.selectById(userId);
        if(userInfo == null || userInfo.getRole() == 1) {
            return R.error()
                    .code(501)
                    .message("用户权限不足");
        } else {
            product product = this.getById(id);
            if (product == null) {
                return R.error()
                        .code(702)
                        .message("商品不存在");
            }
            return R.ok()
                    .code(600)
                    .message("商品查询成功")
                    .data("product", product);
        }

    }
}




