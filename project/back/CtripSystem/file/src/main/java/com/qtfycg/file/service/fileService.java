package com.qtfycg.file.service;

import com.qtfycg.common.R.R;
import org.springframework.web.multipart.MultipartFile;

public interface fileService {


    R upload(MultipartFile file);
}
