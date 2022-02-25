package com.example.demo.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.common.JsonResponse;
import com.example.demo.service.OrderListService;
import com.example.demo.model.domain.OrderList;


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
@RequestMapping("/orderList")
public class OrderListController {

    private final Logger logger = LoggerFactory.getLogger( OrderListController.class );

    @Autowired
    private OrderListService orderListService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        OrderList  orderList =  orderListService.getById(id);
        return JsonResponse.success(orderList);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        orderListService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateOrderList(@PathVariable("id") Integer  id,OrderList  orderList) throws Exception {
        orderList.setId(id);
        orderListService.updateById(orderList);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建OrderList
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(OrderList  orderList) throws Exception {
        orderListService.save(orderList);
        return JsonResponse.success(null);
    }
}

