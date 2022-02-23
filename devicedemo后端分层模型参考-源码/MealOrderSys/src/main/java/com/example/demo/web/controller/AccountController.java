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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Peter Hai
 */
@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public JsonResponse login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        User user = userService.getOne(new QueryWrapper<User>().eq("name",loginDto.getUsername()));
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
                .put("phone",user.getPhone())
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
}

