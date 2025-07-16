package com.qtfycg.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.qtfycg.file.config.OSSConfig;
import com.qtfycg.file.service.fileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class fileServiceImpl implements fileService {

    @Resource
    OSSConfig ossConfig;

    @Override
    public String upload(MultipartFile file) {
        //获取相关配置
        String bucketName = ossConfig.getBucketName();
        String endPoint = ossConfig.getEndPoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();

        //创建OSS对象
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

        //获取原生文件名
        String originalFilename = file.getOriginalFilename();
        //JDK8的日期格式
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //拼装OSS上存储的路径
        String folder = dft.format(time);
        String fileName = generateUUID();
        String extension = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf("."));

        //在OSS上bucket下的文件名
        String uploadFileName = "user/" + folder + "/" + fileName + extension;

        try {
            PutObjectResult result = ossClient.putObject(bucketName, uploadFileName, file.getInputStream());
            //拼装返回路径
            if (result != null) {
                return "https://"+bucketName+"."+endPoint+"/"+uploadFileName;
            }
        } catch (IOException e) {
            log.error("文件上传失败:{}",e.getMessage());
        } finally {
            //OSS关闭服务，不然会造成OOM
            ossClient.shutdown();
        }
        return null;
    }

    private String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }
}
