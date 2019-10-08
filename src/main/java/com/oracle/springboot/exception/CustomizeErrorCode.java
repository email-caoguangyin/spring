package com.oracle.springboot.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你所查找的问题不存在，你换个问题试试?"),
    TARGET_PARAM_NOT_FOUND(2002,"未选择任何问题或者评论进行回复！"),
    NO_LOGIN(2003,"未登录，请先登录！"),
    SYS_ERROR(2004,"服务器在冒烟，请稍后再试！"),
    TARGET_PARAM_WRONG(2005,"评论类型错误或不存在！"),
    COMMENT_NOT_FOUND(2006,"你所查找的评论不存在，你换个问题试试?"),
    COMMENT_IS_EMPTY(2007,"输入内容不能为空！"),
    READ_NOTIFICATION_FAIL(2008,"不能读取他人信息！"),
    NOTIFICATION_NOT_FOUND(2009,"消息不翼而飞了！")
    ;



    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    private String message;
    private Integer code;


    CustomizeErrorCode(Integer code, String message ) {
        this.code = code;
        this.message = message;
    }
}
