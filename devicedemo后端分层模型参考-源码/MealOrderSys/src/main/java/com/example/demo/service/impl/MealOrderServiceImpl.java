package com.example.demo.service.impl;

import com.example.demo.model.domain.MealOrder;
import com.example.demo.mapper.MealOrderMapper;
import com.example.demo.service.MealOrderService;
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
public class MealOrderServiceImpl extends ServiceImpl<MealOrderMapper, MealOrder> implements MealOrderService {
    @Autowired
    private MealOrderMapper mealOrderMapper;
    @Override
    public Integer getMax() {
        return mealOrderMapper.getMax();
    }
}
