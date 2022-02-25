package com.example.demo.service.impl;

import com.example.demo.model.domain.NoticeList;
import com.example.demo.mapper.NoticeListMapper;
import com.example.demo.service.NoticeListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjh
 * @since 2022-02-24
 */
@Service
public class NoticeListServiceImpl extends ServiceImpl<NoticeListMapper, NoticeList> implements NoticeListService {
    @Autowired
    private NoticeListMapper noticeListMapper;
    @Override
    public void deleteByNoticeId(Integer id) {
        noticeListMapper.deleteByNoticeId(id);
    }
}
