package com.oracle.springboot.bean;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private  String name;
    private String loginname;
    private String token;
    private  Date createdate;
    private  Date modifydate;
}
