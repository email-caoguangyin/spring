package com.oracle.springboot.bean;

import com.oracle.springboot.pojo.User;
import lombok.Data;

import java.util.Date;

@Data
public class NotificationModel {
    private Long id;
    private Date createdate;
    private Integer status;
    private Long fabu;
    private String notifiername;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
