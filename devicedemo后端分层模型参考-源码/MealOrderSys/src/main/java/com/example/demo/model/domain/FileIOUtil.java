package com.example.demo.model.domain;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOUtil {
    public Boolean uploadFile(MultipartFile multipartFile) {
        //设置文件名以及路径
        String fileName = new String(multipartFile.getOriginalFilename()
                .substring(multipartFile.getOriginalFilename().indexOf(".")));
        String filePath = "D:\\file" + File.separator + fileName;

        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            if (multipartFile != null) {
                try {
                    //以原来的名称命名,覆盖掉旧的
                    Streams.copy(multipartFile.getInputStream(), new FileOutputStream(filePath), true);
                    //或者下面的
                    // Path path = Paths.get(storagePath);
                    //Files.write(path,multipartFile.getBytes());
                } catch (IOException e) {
                    ExceptionUtils.getFullStackTrace(e);
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
