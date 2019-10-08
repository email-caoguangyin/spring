package com.oracle.springboot.bean;

import lombok.Data;

import java.util.List;
@Data
public class TagModel {
    private String categoryName;
    private List<String> tags;
}
