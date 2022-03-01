package com.example.demo.service;

import com.example.demo.model.domain.Meal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.domain.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjh
 * @since 2022-02-25
 */
public interface MealService extends IService<Meal> {
    List<Meal> getSearch(String string);
    List<Meal> getByType(String string);
}
