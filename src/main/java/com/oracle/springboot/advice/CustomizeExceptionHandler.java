package com.oracle.springboot.advice;

import com.alibaba.fastjson.JSON;
import com.oracle.springboot.exception.CustomizeErrorCode;
import com.oracle.springboot.exception.CustomizeException;
import com.oracle.springboot.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handler(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)){
            //如何请求类型是JSON ,则返回 JSON 类型
            Result result=null;
            if (e instanceof CustomizeException){
                model.addAttribute("message",e.getMessage());
               result =  Result.errorOf((CustomizeException) e);
            }else {
                result =  Result.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json ");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(result));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;
        }else{
            //如果请求类型是 html ，则返回 html 页面
            if (e instanceof CustomizeException){
                model.addAttribute("message",e.getMessage());
            }else {
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");

        }


    }


}
