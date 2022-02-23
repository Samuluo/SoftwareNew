package com.example.demo.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {
    private Integer id;

    private String name;

    private String avatar;

    private String phone;

    private int status;
}

