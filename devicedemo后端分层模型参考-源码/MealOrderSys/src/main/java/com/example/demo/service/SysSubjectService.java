package com.example.demo.service;

import com.example.demo.model.domain.FileIOUtil;
import org.springframework.web.multipart.MultipartFile;

public class SysSubjectService {
    public Boolean uploadSubjectMould(MultipartFile multipartFile) {
        FileIOUtil fileIOUtil = new FileIOUtil();
        Boolean flag = fileIOUtil.uploadFile(multipartFile);
        return flag;
    }
}
