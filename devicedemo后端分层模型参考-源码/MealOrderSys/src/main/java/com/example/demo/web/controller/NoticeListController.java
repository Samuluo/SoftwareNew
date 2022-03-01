package com.example.demo.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.common.JsonResponse;
import com.example.demo.service.NoticeListService;
import com.example.demo.model.domain.NoticeList;


/**
 *
 *  前端控制器
 *
 *
 * @author hjh
 * @since 2022-02-24
 * @version v1.0
 */
@Controller
@RequestMapping("/noticeList")
public class NoticeListController {

    private final Logger logger = LoggerFactory.getLogger( NoticeListController.class );

    @Autowired
    private NoticeListService noticeListService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        NoticeList  noticeList =  noticeListService.getById(id);
        return JsonResponse.success(noticeList);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        noticeListService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateNoticeList(@PathVariable("id") Integer  id,@RequestBody NoticeList  noticeList) throws Exception {
        noticeList.setId(id);
        noticeListService.updateById(noticeList);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建NoticeList
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(@RequestBody NoticeList  noticeList) throws Exception {
        noticeListService.save(noticeList);
        return JsonResponse.success(null);
    }

}

