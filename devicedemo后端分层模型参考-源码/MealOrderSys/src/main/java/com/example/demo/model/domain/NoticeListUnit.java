package com.example.demo.model.domain;

import lombok.Data;

import java.util.List;

@Data
public class NoticeListUnit {
    private Notice notice;
    private List<Integer> list;
}
