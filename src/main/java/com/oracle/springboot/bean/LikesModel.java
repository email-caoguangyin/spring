package com.oracle.springboot.bean;

import lombok.Data;

import java.util.Date;

@Data
public class LikesModel {
    private Long id;
    private Long parentid;
    private Long user;
    private Integer type;
    private Date createdate;
    private Integer status;
    private String outerTitle;
    private String title;



}
