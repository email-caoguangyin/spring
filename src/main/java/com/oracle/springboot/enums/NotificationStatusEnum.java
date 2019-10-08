package com.oracle.springboot.enums;

public enum NotificationStatusEnum {
    READ(1,"已读"),
    UNREAD(0,"未读")
    ;
    private Integer status;
    private String name;

    public Integer getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    NotificationStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }
}
