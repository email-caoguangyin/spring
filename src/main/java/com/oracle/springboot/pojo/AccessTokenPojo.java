package com.oracle.springboot.pojo;

import lombok.Data;

@Data
public class AccessTokenPojo {
    private  String client_id;
    private  String client_secret;
    private  String code;
    private  String redirect_uri;
    private  String state;



}
