package com.example.demo.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.model.domain.NoticeList;
import com.example.demo.model.domain.NoticeListUnit;
import com.example.demo.model.domain.User;
import com.example.demo.service.NoticeListService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.common.JsonResponse;
import com.example.demo.service.NoticeService;
import com.example.demo.model.domain.Notice;

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
@RequestMapping("/notice")
public class NoticeController {

    private final Logger logger = LoggerFactory.getLogger( NoticeController.class );

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeListService noticeListService;

    @Autowired
    private UserService userService;
    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Notice  notice =  noticeService.getById(id);
        return JsonResponse.success(notice);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Integer id) throws Exception {
        noticeService.removeById(id);
        noticeListService.deleteByNoticeId(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateNotice(@PathVariable("id") Integer  id,@RequestBody NoticeListUnit noticeListUnit) throws Exception {
        Notice notice = noticeListUnit.getNotice();
        notice.setId(id);
        List<Integer> list = noticeListUnit.getList();
        noticeService.updateById(notice);
        noticeListService.deleteByNoticeId(id);
        for(int i=0;i<list.size();i++) {
            NoticeList  noticeList = new NoticeList().setNoticeId(id).setUserId(list.get(i));
            noticeListService.save(noticeList);
        }
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Notice
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(@RequestBody NoticeListUnit noticeListUnit) throws Exception {
        Notice notice = noticeListUnit.getNotice();
        noticeService.save(notice);
        Integer id = noticeService.getMax();
        List<Integer> list = noticeListUnit.getList();
        for(int i=0;i<list.size();i++) {
            NoticeList  noticeList = new NoticeList().setNoticeId(id).setUserId(list.get(i));
            noticeListService.save(noticeList);
        }
        return JsonResponse.success(null);
    }

    /**
     * 获取通知列表*/
    //@RequiresAuthentication
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getList() {
        List<Notice> users = new ArrayList<>();
        users = noticeService.list();
//        if(ShiroUtil.getProfile().getStatus()!=0) {
//            return JsonResponse.failure("你的权限不够！");
//        }
        return JsonResponse.success(users);
    }

    /**
     * 描述:根据用户类别创建Notice
     *
     */
    @RequestMapping(value = "/new/{option}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create2(@PathVariable("option") Integer  option,@RequestBody Notice notice) throws Exception {
        noticeService.save(notice);
        Integer id = noticeService.getMax();
        List<Integer> list = new ArrayList<>();
        //所有人
        if(option==-1) {
           for(int i=0;i<3;i++) {
               list.addAll(userService.getSearch2(i));
           }
        } else if(option==0) {
            list = userService.getSearch2(0);
        } else if(option==1) {
            list = userService.getSearch2(1);
        } else if(option==2) {
            list = userService.getSearch2(2);
        }

        for(int i=0;i<list.size();i++) {
            NoticeList  noticeList = new NoticeList().setNoticeId(id).setUserId(list.get(i));
            noticeListService.save(noticeList);
        }
        return JsonResponse.success(null);
    }
}

