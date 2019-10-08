package com.oracle.springboot.bean;

import lombok.Data;

@Data
public class GithubUser {
    private Long id;
    private String name;
    private String bio;
    private String company;
    private  String image;


}
