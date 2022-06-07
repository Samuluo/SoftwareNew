package com.example.demo.demo.service;

import com.example.demo.demo.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjh
 * @since 2022-05-31
 */
@Service
public class AIService{
    public void faceRecognition(String path) throws IOException{
        //前面一半是本地环境下的python的启动文件地址，后面一半是要执行的python脚本地址
        String[] arguments = new String[] {"C:\\Users\\Peter Hai\\Anaconda3\\envs\\pytorch\\python.exe", "E:\\Game\\SoftwareCup\\objectDetection\\test.py",path};
        Process proc;
        try {
            proc = Runtime.getRuntime().exec(arguments);
            // 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            //waitFor是用来显示脚本是否运行成功，1表示失败，0表示成功，还有其他的表示其他错误
            int re = proc.waitFor();
            System.out.println(re);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
