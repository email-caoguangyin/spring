package com.oracle.springboot.bean;

import lombok.Data;

import java.util.Date;

@Data
public class CommentModel {
    private Long id;
    private String content;
    private Integer type;
    private Long parentid;
    private Date createdate;
    private Date modifydate;
    private Integer dianzan;
    private Long createuser;
}
