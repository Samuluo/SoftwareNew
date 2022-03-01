package com.example.demo.service.impl;

import com.example.demo.model.domain.Meal;
import com.example.demo.mapper.MealMapper;
import com.example.demo.model.domain.User;
import com.example.demo.service.MealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjh
 * @since 2022-02-25
 */
@Service
public class MealServiceImpl extends ServiceImpl<MealMapper, Meal> implements MealService {
    @Autowired
    private MealMapper mapper;

    @Override
    public List<Meal> getSearch(String string) {
        return mapper.getSearch(string);
    }

    @Override
    public List<Meal> getByType(String string) {
        return mapper.getByType(string);
    }
}
