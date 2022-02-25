package com.example.demo.service;

import com.example.demo.model.domain.NoticeList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjh
 * @since 2022-02-24
 */
public interface NoticeListService extends IService<NoticeList> {
    void deleteByNoticeId(Integer id);
}
