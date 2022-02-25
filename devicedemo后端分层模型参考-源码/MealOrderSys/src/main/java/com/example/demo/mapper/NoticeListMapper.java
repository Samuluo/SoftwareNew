package com.example.demo.mapper;

import com.example.demo.model.domain.NoticeList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjh
 * @since 2022-02-24
 */
@Mapper
public interface NoticeListMapper extends BaseMapper<NoticeList> {
    void deleteByNoticeId(@Param("id") Integer id);
}
