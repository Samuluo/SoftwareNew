package com.example.demo.mapper;

import com.example.demo.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjh
 * @since 2022-02-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> getSearch(@Param("string") String string);
    List<Integer> getSearch2(@Param("status") Integer status);
}
