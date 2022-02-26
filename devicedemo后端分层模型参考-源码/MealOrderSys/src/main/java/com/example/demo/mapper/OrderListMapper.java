package com.example.demo.mapper;

import com.example.demo.model.domain.OrderList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    void deleteByOrderId(@Param("id") Integer id);
}
