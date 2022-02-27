package com.example.demo.web.controller;

import cn.hutool.core.lang.Assert;
import com.example.demo.shiro.AccountProfile;
import com.example.demo.shiro.JwtToken;
import com.example.demo.shiro.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.common.JsonResponse;
import com.example.demo.service.UserService;
import com.example.demo.model.domain.User;

import javax.websocket.server.PathParam;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


/**
 *
 *  前端控制器
 *
 *
 * @author hjh
 * @since 2022-02-23
 * @version v1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger( UserController.class );

    @Autowired
    private UserService userService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        User  user =  userService.getById(id);
        return JsonResponse.success(user);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        userService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateUser(@PathVariable("id") Integer  id,@RequestBody User  user) throws Exception {
        user.setId(id);
        userService.updateById(user);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建User
    *
    */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(@RequestBody User  user) throws Exception {
        userService.save(user);
        return JsonResponse.success(null);
    }


    /**
     * 获取用户列表*/

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    //@RequiresRoles("0")
    @ResponseBody
    public JsonResponse getList() {
        List<User> users = new ArrayList<>();
        users = userService.list();
/*        if(ShiroUtil.getProfile().getStatus()!=0) {
            return JsonResponse.failure("你的权限不够！");
        }*/
        return JsonResponse.success(users);
    }
   /**
    *
    * 4. 查询员工信息：根据关键字 检索*/
   @RequestMapping(value="/search",method=RequestMethod.POST)
   @ResponseBody
   public JsonResponse getSearchResults(@RequestBody String string) {
        List<User> users = new ArrayList<User>();
        string = string.replace("=","");
        //巨坑的url解码，测了好半天
        String s = URLDecoder.decode(string);
        users = userService.getSearch("%"+s+"%");
        return JsonResponse.success(users);
   }
}

