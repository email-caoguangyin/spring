package com.oracle.springboot.enums;

public enum LikeStatusEnum {
    LIKE(1,"点赞成功！"),
    UNLIKE(0,"未点赞！"),
    HATE(3,"厌恶！"),
    ;
    private Integer status;
    private String name;

    public Integer getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    LikeStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

}
