package com.example.demo.web.controller;

import com.example.demo.common.JsonResponse;
import com.example.demo.service.SysSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

public class FileController {
    @Autowired
    private SysSubjectService sysSubjectService;
    /**
     * 文件上传
     */
    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public JsonResponse uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        sysSubjectService.uploadSubjectMould(multipartFile);
        return JsonResponse.success("上传完成");
    }
}
