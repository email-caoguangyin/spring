package com.oracle.springboot.contorller;

import com.oracle.springboot.bean.NotificationModel;
import com.oracle.springboot.bean.QuestionPage;
import com.oracle.springboot.pojo.User;
import com.oracle.springboot.service.NotificationService;
import com.oracle.springboot.service.QuestionService;
import com.oracle.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**我的问题
 *
 */
@Controller
public class ProfileController {
    private String name;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;

    /**
     *
     * @param action
     * @param request
     * @param model
     * @param page
     * @param size
     * @return 我的问题
     */
    @GetMapping("/profile/{action}")
    public String Profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request, Model model,
                          @RequestParam(name="page" ,defaultValue = "1")Integer page,
                          @RequestParam(name="size" ,defaultValue = "5")Integer size){
        User user= (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }
        if ("question".equals(action)){
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的问题");
            QuestionPage questionPage=questionService.getQuestionPageByUser(user.getId(),size,page);
            model.addAttribute("pagination",questionPage);

        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            QuestionPage questionPage=notificationService.getNotificationPageByUser(user.getId(),size,page);
            model.addAttribute("pagination",questionPage);

        }

        //我的问题数
        Integer questionCount=questionService.getCountByUser(user.getId());
        model.addAttribute("questionCount",questionCount);
        //通知数
        Integer unreadCount= notificationService.unread(user);
        model.addAttribute("unreadCount",unreadCount);
       return "profile";
    }
}
