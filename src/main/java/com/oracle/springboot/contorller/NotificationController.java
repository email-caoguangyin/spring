package com.oracle.springboot.contorller;

import com.oracle.springboot.bean.NotificationModel;
import com.oracle.springboot.enums.NotificationTypeEnum;
import com.oracle.springboot.pojo.Comment;
import com.oracle.springboot.pojo.User;
import com.oracle.springboot.service.CommentService;
import com.oracle.springboot.service.NotificationService;
import com.oracle.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;


    @GetMapping("/notification/{id}")
    public String notification(HttpServletRequest request,@PathVariable(name = "id") Long id){
       User user= (User) request.getSession().getAttribute("user");
       if (user==null){
          return  "redirect:/";
       }
        NotificationModel notificationModel=notificationService.read(id,user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType()==notificationModel.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType()==notificationModel.getType()){
                return "redirect:/question/" + notificationModel.getOuterid();
        }else if (NotificationTypeEnum.LIKE_COMMENT.getType()==notificationModel.getType()){
            Comment comment=commentService.getCommentById(notificationModel.getOuterid());
           /* QuestionModel questionModel=questionService.getQuestionById(comment.getParentid());*/
            return "redirect:/question/" + comment.getParentid();
        }
        else{
            return  "redirect:/";
        }
    }
}
