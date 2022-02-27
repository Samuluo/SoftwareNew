package com.example.demo.mapper;

import com.example.demo.model.domain.OrderList;
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
 * @since 2022-02-25
 */
@Mapper
public interface OrderListMapper extends BaseMapper<OrderList> {
    List<OrderList> getByOrderId(@Param("id") Integer id);
    void deleteByOrderId(@Param("id") Integer id);
}
