package com.qtfycg.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.admin.domain.Vo.createVo;
import com.qtfycg.admin.domain.entity.hotel;
import com.qtfycg.admin.domain.entity.product;
import com.qtfycg.admin.feign.fileFeign;
import com.qtfycg.admin.mapper.hotelMapper;
import com.qtfycg.admin.service.hotelService;
import com.qtfycg.admin.service.productService;
import com.qtfycg.common.R.R;
import com.qtfycg.common.SnowflakeId.SnowflakeIdGenerator;
import com.qtfycg.common.annotation.Login;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class hotelServiceImpl extends ServiceImpl<hotelMapper, hotel>
    implements hotelService{

    @Resource
    productService productService;
    @Resource
    fileFeign fileFeign;


    @Login
    @Override
    public R createHotel(createVo createVo,
                         List<MultipartFile> files) {
        QueryWrapper<hotel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("location", createVo.getLocation());
        queryWrapper.eq("name", createVo.getName());
        hotel existingHotel = this.getOne(queryWrapper);
        if (existingHotel != null) {
            return R.error()
                    .code(701)
                    .message("酒店已存在，请勿重复添加");
        }else {
            hotel hotel = new hotel();
            SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 3);
            long id = idGenerator.nextId();
            hotel.setId(id);
            StringBuilder avatarUrls = new StringBuilder();
            if (files != null && !files.isEmpty()) {
                for (MultipartFile file : files) {
                    R uploadResult = fileFeign.upload(file);
                    if (uploadResult.getCode() != 1000) {
                        return R.error()
                                .code(700)
                                .message("图片上传失败");
                    }
                    avatarUrls.append(uploadResult.getData().toString()).append(",");
                }
                // 移除最后一个逗号
                hotel.setAvatar(avatarUrls.substring(0, avatarUrls.length() - 1));
            }
            hotel.setLocation(createVo.getLocation());
            hotel.setPrice(createVo.getPrice());
            hotel.setStock(createVo.getStock());
            hotel.setDescription(createVo.getDescription());
            product product = new product();
            product.setId(id);
            product.setPrice(createVo.getPrice());
            product.setStatus(1);
            product.setStock(createVo.getStock());
            product.setName(createVo.getName());
            product.setType(1);
            product.setDescription(createVo.getDescription());
            this.save(hotel);
            productService.saveProduct(product);
            return R.ok()
                    .code(200)
                    .data("data", hotel)
                    .message("酒店创建成功");
        }
    }
}




