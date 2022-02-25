package com.example.demo.web.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.dto.LoginDto;
import com.example.demo.common.JsonResponse;
import com.example.demo.model.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.shiro.util.JwtUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.example.demo.common.Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author Peter Hai
 */
@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Resource
    private DefaultKaptcha captchaProducer;
    /**
     * 登录验证码SessionKey
     */
    public static final String LOGIN_VALIDATE_CODE = "login_validate_code";

    @PostMapping("/login")
    public JsonResponse login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResponse.failure(bindingResult.getFieldError().getDefaultMessage());
        }
        User user = userService.getOne(new QueryWrapper<User>().eq("name",loginDto.getName()));
        //对象封装操作类
            Assert.notNull(user,"用户不存在");
            if (!(SecureUtil.md5(user.getPassword())).equals(SecureUtil.md5(loginDto.getPassword()))){
            return JsonResponse.failure("密码不正确");
            }
        String jwt = jwtUtils.generateToken(user.getId());
            response.addHeader("Authorization",jwt);
            response.setHeader("Access-control-Expose-Headers",jwt);
            return JsonResponse.success(MapUtil.builder()
                    .put("id",user.getId())
                    .put("name",user.getName())
                    .put("avatar",user.getAvatar())
                    .put("status",user.getStatus())
                    .put("token",jwt)
                    .map()
            );
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public JsonResponse logout() {
        SecurityUtils.getSubject().logout();
        return JsonResponse.success(null);
    }

    /**
     * 登录验证码图片
     */
    @RequestMapping(value = {"/loginValidateCode"})
    public void loginValidateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Util.validateCode(request,response,captchaProducer,LOGIN_VALIDATE_CODE);
    }
    /**
     * 检查验证码是否正确
     */
    @RequestMapping("/checkLoginValidateCode")
    @ResponseBody
    public JsonResponse checkLoginValidateCode(HttpServletRequest request, @RequestParam("validateCode")String validateCode) {
        String loginValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        HashMap<String,Object> map = new HashMap<String,Object>();
        if(loginValidateCode == null){
            return JsonResponse.failure("验证码过期");
        }else if(loginValidateCode.equals(validateCode)){
            return JsonResponse.success("验证码正确");
        }
        return JsonResponse.failure("验证码错误");
    }

}

