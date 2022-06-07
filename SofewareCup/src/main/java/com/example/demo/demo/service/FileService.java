package com.example.demo.demo.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface FileService {

    String upload(MultipartFile file) throws IOException;

}
