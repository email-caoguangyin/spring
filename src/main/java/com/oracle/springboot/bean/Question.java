package com.oracle.springboot.bean;

import lombok.Data;

import java.util.Date;
@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private Date createdate;
    private Date modifydate;
    private Integer createUser;
    private Integer yuedu;
    private Integer dianzan;
    private Integer pinglun;
    private String tag;


}
