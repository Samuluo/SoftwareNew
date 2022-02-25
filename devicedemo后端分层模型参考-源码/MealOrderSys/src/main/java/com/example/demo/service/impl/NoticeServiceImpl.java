package com.example.demo.service.impl;

import com.example.demo.model.domain.Notice;
import com.example.demo.mapper.NoticeMapper;
import com.example.demo.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjh
 * @since 2022-02-25
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;
    @Override
    public Integer getMax() {
        return noticeMapper.getMax();
    }
}
