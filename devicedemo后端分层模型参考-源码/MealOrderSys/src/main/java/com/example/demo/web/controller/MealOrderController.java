package com.example.demo.web.controller;

import com.example.demo.model.domain.*;
import com.example.demo.service.OrderListService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.common.JsonResponse;
import com.example.demo.service.MealOrderService;

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
@RequestMapping("/order")
public class MealOrderController {

    private final Logger logger = LoggerFactory.getLogger( MealOrderController.class );

    @Autowired
    private MealOrderService mealOrderService;

    @Autowired
    private OrderListService orderListService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        MealOrder mealOrder =  mealOrderService.getById(id);
        return JsonResponse.success(mealOrder);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Integer id) throws Exception {
        mealOrderService.removeById(id);
        orderListService.deleteByOrderId(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateOrder(@PathVariable("id") Integer  id,@RequestBody OrderListUnit  orderListUnit) throws Exception {

        MealOrder mealOrder = orderListUnit.getMealOrder();
        mealOrder.setId(id);
        List<Pair> list = orderListUnit.getOrderList();
        mealOrderService.updateById(mealOrder);
        orderListService.deleteByOrderId(id);
        for(int i=0;i<list.size();i++) {
            OrderList  orderList = new OrderList().setOrderId(id).setMealId(list.get(i).getX()).setMealNumber(list.get(i).getY());
            orderListService.save(orderList);
        }
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Order
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(@RequestBody OrderListUnit orderListUnit) throws Exception {
        MealOrder mealOrder = orderListUnit.getMealOrder();
        mealOrderService.save(mealOrder);
        Integer id = mealOrderService.getMax();
        List<Pair> list = orderListUnit.getOrderList();
        for(int i=0;i<list.size();i++) {
            OrderList orderList = new OrderList().setOrderId(id).setMealId(list.get(i).getX()).setMealNumber(list.get(i).getY());
            orderListService.save(orderList);
        }
        return JsonResponse.success(null);
    }

    /**
     * 获取订单列表*/
    //@RequiresAuthentication
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getList() {
        List<MealOrder> users = new ArrayList<>();
        users = mealOrderService.list();
//        if(ShiroUtil.getProfile().getStatus()!=0) {
//            return JsonResponse.failure("你的权限不够！");
//        }
        return JsonResponse.success(users);
    }
}

