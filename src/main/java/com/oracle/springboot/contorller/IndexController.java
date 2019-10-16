package com.oracle.springboot.contorller;


import com.oracle.springboot.bean.QuestionPage;

import com.oracle.springboot.bean.QuestionQueryDto;
import com.oracle.springboot.exception.CustomizeErrorCode;
import com.oracle.springboot.exception.CustomizeException;
import com.oracle.springboot.pojo.Notification;
import com.oracle.springboot.service.NotificationService;
import com.oracle.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
                        @RequestParam(name="size" ,defaultValue = "5")Integer size,
                        @RequestParam(name="search" ,required = false)String search){

        try {

        QuestionPage questionPage=questionService.getQuestionPage(page,size,search);
        model.addAttribute("pagination",questionPage);
        model.addAttribute("search",search);
        return "top";
        } catch (Exception e) {
            model.addAttribute("pagination",new QuestionPage<>());
            model.addAttribute("search",search);
            return "top";
        }
    }


   // doomsday
    @RequestMapping("/doomsday")
    public String doomsday(@RequestParam(name="page" ,defaultValue = "1")Integer page,
                           @RequestParam(name="size" ,defaultValue = "2")Integer size,
                           @RequestParam(name="search" ,required = false)String search){
        QuestionQueryDto questionQueryDto=new QuestionQueryDto();
        questionQueryDto.setPage(page);
        questionQueryDto.setSize(size);
        questionQueryDto.setSearch(search);
        try {
            List<Notification> notificationList = notificationService.selectDoomsd(questionQueryDto);
        } catch (Exception e) {
            throw  new CustomizeException(CustomizeErrorCode.DOOMSDAY);
        }
        return null;
    }
}
