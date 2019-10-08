package com.oracle.springboot.bean;


import com.oracle.springboot.pojo.User;
import lombok.Data;

import java.util.Date;
@Data
public class CommentModel2 {
    private Long id;
    private String content;
    private Integer type;
    private Long parentid;
    private Date createdate;
    private Date modifydate;
    private Integer dianzan;
    private Long createuser;
    private User user;
    private  Integer pingluns;
}
