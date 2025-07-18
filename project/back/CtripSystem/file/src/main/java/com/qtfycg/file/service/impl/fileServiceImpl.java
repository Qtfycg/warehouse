package com.qtfycg.file.service.impl;

import com.qtfycg.common.R.R;
import com.qtfycg.file.config.OSSConfig;
import com.qtfycg.file.service.fileService;
import com.qtfycg.file.utils.OssUploader;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class fileServiceImpl implements fileService {

    @Resource
    OSSConfig ossConfig;
    @Resource
    OssUploader ossUploader;

    @Override
    public R upload(MultipartFile file) {
        if (file.isEmpty()) {
            return R.error()
                    .code(1100)
                    .message("文件上传失败");
        }
        try {
            String url = ossUploader.upload(file);
            log.info("✅ 文件上传到 OSS 成功，URL: {}", url);
            return R.ok()
                    .code(1000)
                    .message("文件上传成功")
                    .data("url", url);
        } catch (Exception e) {
            log.error("❌ OSS 上传失败", e);
            return R.error().message("OSS 上传失败：" + e.getMessage());
        }
    }
}
