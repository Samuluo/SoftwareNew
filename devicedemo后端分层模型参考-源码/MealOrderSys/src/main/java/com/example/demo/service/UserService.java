package com.example.demo.service;

import com.example.demo.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjh
 * @since 2022-02-23
 */
public interface UserService extends IService<User> {
    List<User> getSearch( String string);
}
