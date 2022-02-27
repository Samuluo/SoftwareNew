package com.example.demo.model.domain;

import lombok.Data;

import java.util.List;

/**
 * @author Peter Hai
 */
@Data
public class OrderListUnit {
    private MealOrder mealOrder;
    private List<Pair> orderList;

    void setList(List<Pair> list) {
        this.orderList = list;
    }
}

