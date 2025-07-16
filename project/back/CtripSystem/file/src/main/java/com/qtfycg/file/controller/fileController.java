package com.qtfycg.file.controller;

import com.qtfycg.file.service.fileService;
import jakarta.annotation.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
public class fileController {
    @Resource
    fileService fileService;


    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestPart("file") MultipartFile file){
        String imgFileStr = fileService.upload(file);
        return buildResult(imgFileStr);
    }
    private Map<String,Object> buildResult(String str){
        Map<String, Object> result = new HashMap<>();
        //判断字符串用lang3下的StringUtils去判断，这块我就不引入新的依赖了
        if(str== null || str.isEmpty()){
            result.put("code",10000);
            result.put("msg","图片上传失败");
            result.put("data",null);
        }else{
            result.put("code",200);
            result.put("msg","图片上传成功");
            result.put("data",str);
        }
        return result;
    }
}
