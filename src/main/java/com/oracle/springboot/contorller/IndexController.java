package com.oracle.springboot.contorller;


import com.oracle.springboot.bean.QuestionPage;
import com.oracle.springboot.pojo.User;
import com.oracle.springboot.service.NotificationService;
import com.oracle.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 首页与登出
 *
 */

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    /**
     *
     * @param model
     * @param request
     * @param page
     * @param size
     * @return 进入首页
     */
    @GetMapping("/")
    public String hello(Model model , HttpServletRequest request,
                        @RequestParam(name="page" ,defaultValue = "1")Integer page,
                        @RequestParam(name="size" ,defaultValue = "5")Integer size){


        User user= (User) request.getSession().getAttribute("user");
        QuestionPage questionPage=questionService.getQuestionPage(page,size);
        model.addAttribute("pagination",questionPage);
        return "top";
    }


}
