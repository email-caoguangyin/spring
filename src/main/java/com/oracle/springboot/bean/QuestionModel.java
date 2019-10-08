package com.oracle.springboot.bean;

import com.oracle.springboot.pojo.User;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionModel {
    private Long id;
    private String title;
    private String description;
    private Date createdate;
    private Date modifydate;
    private Long createuser;
    private Integer yuedu;
    private Integer dianzan;
    private Integer pinglun;
    private String tag;
    private User user;
    private  String name;


}
