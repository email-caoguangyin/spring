package com.oracle.springboot.bean;

import lombok.Data;

@Data
public class QuestionQueryDto {
    private String search;
    private Integer size;
    private Integer page;

}
