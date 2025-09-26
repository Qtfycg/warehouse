package com.qtfycg.admin.controller;

import com.qtfycg.admin.domain.Vo.createVo;
import com.qtfycg.admin.service.hotelService;
import com.qtfycg.common.R.R;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class hotelController {
    @Resource
    hotelService hotelService;

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R createFlight(@RequestPart("createVo") createVo createVo,
                          @RequestPart(value = "file") List<MultipartFile> files) throws Exception {
        return hotelService.createHotel(createVo, files);
    }
}
