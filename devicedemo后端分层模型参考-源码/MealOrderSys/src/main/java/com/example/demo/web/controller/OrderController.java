package com.example.demo.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.common.JsonResponse;
import com.example.demo.service.OrderService;
import com.example.demo.model.domain.Order;


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
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger( OrderController.class );

    @Autowired
    private OrderService orderService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Order  order =  orderService.getById(id);
        return JsonResponse.success(order);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        orderService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateOrder(@PathVariable("id") Long  id,Order  order) throws Exception {
        order.setId(id);
        orderService.updateById(order);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Order
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Order  order) throws Exception {
        orderService.save(order);
        return JsonResponse.success(null);
    }
}

