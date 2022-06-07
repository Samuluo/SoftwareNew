package com.example.demo.demo.web.controller;

import com.example.demo.demo.common.JsonResponse;
import com.example.demo.demo.common.QiniuCloudUtil;
import com.example.demo.demo.service.AIService;
import com.example.demo.demo.service.ChangeDetectionService;
import com.example.demo.demo.service.FileService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Peter Hai
 */
@RestController
@RequestMapping("/changeDetection")
@Slf4j
public class changeDetectionController {
    @Autowired
    private AIService aiService;
    @Autowired
    private ChangeDetectionService changeDetectionService;
    protected FileService fileService;

    protected ResourceLoader resourceLoader;

    public changeDetectionController(FileService fileService, ResourceLoader resourceLoader) {
        this.fileService = fileService;
        this.resourceLoader = resourceLoader;
    }

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2,HttpServletRequest request) throws IOException {
        //本地上传
        Map<String, String> map = new HashMap<String, String>();
        String path1 = fileService.upload(file);
        String path2 = fileService.upload(file2);
        System.out.println(path1 + " " + path2);
        changeDetectionService.changeDetection(path1,path2);
        return ResponseEntity.ok().body(map);
    }

/*    @RequestMapping("/ai")
    public JsonResponse ai(MultipartFile file) throws IOException {
        aiService.faceRecognition(file);
        return new JsonResponse().setCode(200).setMessage("返回了");
    }*/

    /**
     * 七牛云文件上传
     */
    @ResponseBody
    @RequestMapping(value="/uploadImg", method= RequestMethod.POST)
    public JsonResponse uploadImg(@RequestParam("file") MultipartFile image, HttpServletRequest request) throws IOException {
        JsonResponse result = new JsonResponse();

        if (image.isEmpty()) {
            result.setCode(400);
            result.setMessage("文件为空，请重新上传");
            return result;
        }

        try {
            byte[] bytes = image.getBytes();
            String imageName = UUID.randomUUID().toString();
            try {
                //使用base64方式上传到七牛云
                String url = QiniuCloudUtil.put64image(bytes, imageName);
                result.setCode(200);
                result.setMessage("文件上传成功");
                result.setData(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            result.setCode(500);
            result.setMessage("文件上传发生异常！");
        } finally {
            //aiService.faceRecognition(image);

            return result;
        }

    }
}
