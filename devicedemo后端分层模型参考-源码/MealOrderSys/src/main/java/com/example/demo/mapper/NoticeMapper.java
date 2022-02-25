package com.example.demo.mapper;

import com.example.demo.model.domain.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjh
 * @since 2022-02-25
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    Integer getMax();
}
