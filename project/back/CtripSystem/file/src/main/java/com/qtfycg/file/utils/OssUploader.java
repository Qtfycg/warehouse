package com.qtfycg.file.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.qtfycg.file.config.OSSConfig;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Component
public class OssUploader {
    @Resource
    OSSConfig ossConfig;

    private OSS ossClient;

    @PostConstruct
    public void init() {
        ossClient = new OSSClientBuilder()
                .build(ossConfig.getEndpoint(),
                        ossConfig.getAccessKeyId(),
                        ossConfig.getAccessKeySecret());
    }

    public String upload(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID() + ext;
        String key = ossConfig.getDir() + filename;

        try (InputStream inputStream = file.getInputStream()) {
            ossClient.putObject(ossConfig.getBucketName(), key, inputStream);
        }

        return "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/" + key;
    }
}
