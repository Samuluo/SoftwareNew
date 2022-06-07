package com.example.demo.demo.service.impl;


import com.example.demo.demo.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMM");



    @Override
    public String upload(MultipartFile file) throws IOException {
        String map = storeFile(file);
        return map;
    }

    private static String storeFile(MultipartFile file) throws IOException {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
            //File upload = new File("D://img/static/changeDetectorPredict/uploads");
            File upload = new File("C://Users/Peter Hai/Documents/Tencent Files/1556332597/FileRecv/SofewareCup/src/main/resources/static/changeDetectorPredict/uploads");
            String yearMonth = SDF.format(new Date());//当前年月
            String fileName = file.getOriginalFilename();//文件全名
            System.out.println(fileName);
            File parentDir = new File(upload.getAbsolutePath()+"/" + yearMonth);
            if(!upload.exists()){
                boolean wasSuccessful = upload.mkdirs();
                if (!wasSuccessful) {
                    System.out.println("was not successful.");
                }
            }
            if(!parentDir.exists()){
                parentDir.mkdirs();
            }
            String suffix = suffix(fileName);//文件后缀
            String relPath = "/" + yearMonth + "/" + "-" + UUID.randomUUID().toString().replaceAll("-","") + suffix;
            File fileUp = new File(upload.getAbsolutePath()+ relPath);
            file.transferTo(fileUp);
            Map<String, String> map = new HashMap();
            map.put("url", "C://Users/Peter Hai/Documents/Tencent Files/1556332597/FileRecv/SofewareCup/src/main/resources/static/changeDetectorPredict/uploads" + relPath);
            log.info(relPath);
            return "C://Users/Peter Hai/Documents/Tencent Files/1556332597/FileRecv/SofewareCup/src/main/resources/static/changeDetectorPredict/uploads" + relPath;
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 后缀名或empty："a.png" => ".png"
     */
    private static String suffix(String fileName) {
        int i = fileName.lastIndexOf('.');
        return i == -1 ? "" : fileName.substring(i);
    }
}
