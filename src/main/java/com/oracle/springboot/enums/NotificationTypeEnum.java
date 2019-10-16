package com.oracle.springboot.enums;

public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论"),
    LIKE_QUESTION(3,"点赞了问题"),
    LIKE_COMMENT(4,"点赞了评论")
    ;
    private Integer type;
    private String name;

    public static String nameOfType(Integer type) {
        for (NotificationTypeEnum notificationTypeEnum:NotificationTypeEnum.values()) {
            if (notificationTypeEnum.getType()==type){
                return notificationTypeEnum.getName();
            }

        }
        return "";
    }

    public Integer getType() { return type;}
    public String getName() {
        return name;
    }

    NotificationTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
}
