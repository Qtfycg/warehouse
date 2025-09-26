package com.qtfycg.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.admin.domain.Vo.createVo;
import com.qtfycg.admin.domain.entity.hotel;
import com.qtfycg.common.R.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface hotelService extends IService<hotel> {

    R createHotel(createVo createVo, List<MultipartFile> files) throws Exception;
}
