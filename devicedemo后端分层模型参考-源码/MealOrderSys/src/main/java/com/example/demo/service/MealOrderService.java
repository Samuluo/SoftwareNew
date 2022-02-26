package com.example.demo.service;

import com.example.demo.model.domain.MealOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjh
 * @since 2022-02-25
 */
public interface MealOrderService extends IService<MealOrder> {
    Integer getMax();
}
