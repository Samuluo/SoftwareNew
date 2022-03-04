package com.example.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.JsonResponse;
import com.example.demo.common.TxCloudSmsUtil;
import com.example.demo.model.domain.SmsParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Peter Hai
 * @program: SpringBoot_01
 * @description: 短信发送
 * @createDate: 2021-04-27 22:24
 **/
@Controller
@RequestMapping("/code")
public class SendCodeController {
    /**
     * 发送验证码
     * */
    private String Vcode = "";

    @RequestMapping(value = "/sendCode", method=RequestMethod.POST)
    @ResponseBody
    public JsonResponse getCode(@RequestBody  String memPhone){

        JSONObject json = null;
        //随机生成验证码
        String code = String.valueOf(new Random().nextInt(999999));
        Vcode = code;
        SmsParams smsParams = new SmsParams(memPhone,code);
        TxCloudSmsUtil send = new TxCloudSmsUtil();
        send.sendSms(smsParams);
        return JsonResponse.success(null);
    }
}

