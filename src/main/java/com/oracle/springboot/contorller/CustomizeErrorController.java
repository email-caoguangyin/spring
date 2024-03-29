package com.oracle.springboot.contorller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/** 异常处理
 *
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomizeErrorController implements ErrorController{
    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, Model model){
        HttpStatus status=getStatus(request);
        if (status.is4xxClientError()){
            model.addAttribute("message","你的请求出错，换个方式试试！手写404");
        }
        if (status.is5xxServerError()){
            model.addAttribute("message","服务器冒烟了，请你稍后再试！手写500");
        }
        return new ModelAndView("error");
    }


    private HttpStatus getStatus(HttpServletRequest request) {
            Integer statusCode= (Integer) request.getAttribute("javax.servlet.error.status_code");
            if (statusCode ==null){
                return  HttpStatus.INTERNAL_SERVER_ERROR;
            }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception e) {
         return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}
