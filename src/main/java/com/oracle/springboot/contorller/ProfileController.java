package com.oracle.springboot.contorller;

import com.oracle.springboot.bean.NotificationModel;
import com.oracle.springboot.bean.QuestionPage;
import com.oracle.springboot.enums.QuestionStatusEnum;
import com.oracle.springboot.pojo.User;
import com.oracle.springboot.service.LikesService;
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

    @Autowired
    private LikesService likesService;

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
            Integer status=QuestionStatusEnum.QUESTION_EXIST.getType();
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的问题");
            QuestionPage questionPage=questionService.getQuestionPageByUser(user.getId(),size,page,status);
            model.addAttribute("pagination",questionPage);

        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            QuestionPage questionPage=notificationService.getNotificationPageByUser(user.getId(),size,page);
            model.addAttribute("pagination",questionPage);

        }else  if ("likes".equals(action)){
            model.addAttribute("section","likes");
            model.addAttribute("sectionName","点赞问题");
            QuestionPage questionPage=likesService.getLikesPageByUser(user.getId(),size,page);
            model.addAttribute("pagination",questionPage);
        }else if ("hate".equals(action)) {
            Integer status = QuestionStatusEnum.QUESTION_VANISH.getType();
            model.addAttribute("section", "hate");
            model.addAttribute("sectionName", "黑名单");
            QuestionPage questionPage = questionService.getQuestionPageByUser(user.getId(), size, page, status);
            model.addAttribute("pagination", questionPage);
        }
        //我的问题数
        Integer status=QuestionStatusEnum.QUESTION_EXIST.getType();
        Integer questionCount=questionService.getCountByUser(user.getId(),status);
        model.addAttribute("questionCount",questionCount);

        //通知数
        Integer unreadCount= notificationService.unread(user);
        model.addAttribute("unreadCount",unreadCount);
        //点赞问题数
        Integer likesCount= likesService.getCountByUser(user);
        model.addAttribute("likesCount",likesCount);
        //黑名单数
        Integer hatestatus=QuestionStatusEnum.QUESTION_VANISH.getType();
        Integer hateCount= questionService.getCountByUser(user.getId(),hatestatus);
        model.addAttribute("hateCount",hateCount);
        
       return "profile";
    }
}
