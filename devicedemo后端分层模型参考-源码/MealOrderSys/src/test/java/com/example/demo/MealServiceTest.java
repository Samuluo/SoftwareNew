package com.example.demo;

import com.example.demo.model.domain.Meal;
import com.example.demo.service.MealService;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class MealServiceTest {
    @Resource
    MealService mealService;
    @Test
    public void AddDefault() {
        List<Meal> list = mealService.list();
        for(int i=0;i<list.size();i++) {
            list.get(i).setNum(0);
        }
        mealService.saveBatch(list);
        System.out.println(list);
    }
}
