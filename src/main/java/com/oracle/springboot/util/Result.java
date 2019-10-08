package com.oracle.springboot.util;

import com.oracle.springboot.exception.CustomizeErrorCode;
import com.oracle.springboot.exception.CustomizeException;
import lombok.Data;

@Data
public class Result <T>{
    private Integer code;
    private String message;
    private T data;


    public static Result errorOf(Integer code,String message ) {
        Result result=new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;

    }

    public static Result errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }
    public static Result errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }

    public static Result okOf() {
        Result result=new Result();
        result.setCode(200);
        result.setMessage("成功");
        return result;
    }

    public static <T> Result okOf(T t) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("请求成功");
        result.setData(t);
        return result;
    }
}
