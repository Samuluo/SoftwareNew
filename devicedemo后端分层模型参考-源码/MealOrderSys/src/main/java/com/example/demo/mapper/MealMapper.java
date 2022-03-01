package com.example.demo.mapper;

import com.example.demo.model.domain.Meal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjh
 * @since 2022-02-25
 */
@Mapper
public interface MealMapper extends BaseMapper<Meal> {
    List<Meal> getSearch(@Param("string") String string);
    List<Meal> getByType(@Param("string") String string);
}
