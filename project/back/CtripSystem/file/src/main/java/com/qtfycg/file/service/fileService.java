package com.qtfycg.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface fileService {


    String upload(MultipartFile file);
}
