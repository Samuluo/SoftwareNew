package com.example.demo.service;

import com.example.demo.model.domain.OrderList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjh
 * @since 2022-02-25
 */
public interface OrderListService extends IService<OrderList> {
    List<OrderList> getByOrderId(Integer id);
    void deleteByOrderId(Integer id);
}
