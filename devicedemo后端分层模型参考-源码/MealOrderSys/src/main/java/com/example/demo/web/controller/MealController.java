package com.example.demo.web.controller;

import com.example.demo.model.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.common.JsonResponse;
import com.example.demo.service.MealService;
import com.example.demo.model.domain.Meal;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


/**
 *
 *  前端控制器
 *
 *
 * @author hjh
 * @since 2022-02-25
 * @version v1.0
 */
@Controller
@RequestMapping("/meal")
public class MealController {

    private final Logger logger = LoggerFactory.getLogger( MealController.class );

    @Autowired
    private MealService mealService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Meal  meal =  mealService.getById(id);
        return JsonResponse.success(meal);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        mealService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateMeal(@PathVariable("id") Integer  id,@RequestBody Meal  meal) throws Exception {
        meal.setId(id);
        mealService.updateById(meal);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Meal
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(@RequestBody  Meal  meal) throws Exception {
        mealService.save(meal);
        return JsonResponse.success(null);
    }

    /**
     * 获取菜品列表*/
    //@RequiresAuthentication
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getList() {
        List<Meal> meals = new ArrayList<>();
        meals = mealService.list();
//        if(ShiroUtil.getProfile().getStatus()!=0) {
//            return JsonResponse.failure("你的权限不够！");
//        }

        return JsonResponse.success(meals);
    }

    /**
     *
     * 4. 查询菜品信息：根据关键字 检索*/
    @RequestMapping(value="/search",method=RequestMethod.POST)
    @ResponseBody
    public JsonResponse getSearchResults(@RequestBody String string) {
        List<Meal> meals = new ArrayList<Meal>();
        string = string.replace("=","");
        //巨坑的url解码，测了好半天
        String s = URLDecoder.decode(string);
        System.out.println(s);
        meals = mealService.getSearch("%"+s+"%");
        return JsonResponse.success(meals);
    }

    /**
     *
     * 4. 查询菜品信息：根据类型 检索*/
    @RequestMapping(value="/searchByType",method=RequestMethod.POST)
    @ResponseBody
    public JsonResponse getSearchResults2(@RequestBody String string) {
        List<Meal> meals = new ArrayList<Meal>();
        string = string.replace("=","");
        //巨坑的url解码，测了好半天
        String s = URLDecoder.decode(string);
        System.out.println(s);
        meals = mealService.getByType("%"+s+"%");
        return JsonResponse.success(meals);
    }
}

