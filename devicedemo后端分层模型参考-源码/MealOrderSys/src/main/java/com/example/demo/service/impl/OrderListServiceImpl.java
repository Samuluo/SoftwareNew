package com.example.demo.service.impl;

import com.example.demo.model.domain.OrderList;
import com.example.demo.mapper.OrderListMapper;
import com.example.demo.service.OrderListService;
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
public class OrderListServiceImpl extends ServiceImpl<OrderListMapper, OrderList> implements OrderListService {
    @Autowired
    private OrderListMapper orderListMapper;

    @Override
    public List<OrderList> getByOrderId(Integer id) {
        return orderListMapper.getByOrderId(id);
    }

    @Override
    public void deleteByOrderId(Integer id) {
        orderListMapper.deleteByOrderId(id);
    }
}
