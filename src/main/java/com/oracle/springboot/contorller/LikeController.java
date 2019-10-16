package com.oracle.springboot.contorller;

import com.oracle.springboot.bean.QuestionModel;
import com.oracle.springboot.enums.CommentTypeEnum;
import com.oracle.springboot.enums.LikeStatusEnum;
import com.oracle.springboot.exception.CustomizeErrorCode;
import com.oracle.springboot.pojo.Comment;
import com.oracle.springboot.pojo.Likes;
import com.oracle.springboot.pojo.Question;
import com.oracle.springboot.pojo.User;
import com.oracle.springboot.service.CommentService;
import com.oracle.springboot.service.LikesService;
import com.oracle.springboot.service.QuestionService;
import com.oracle.springboot.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LikeController {

    @Autowired
    private LikesService likesService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;



    @ResponseBody
    @RequestMapping(value = "/like",method = RequestMethod.POST)
    public Result<Comment> like(@RequestBody Comment comment, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        List<Likes> like = likesService.getLike(user, comment);
        if (like.size()==0 || like.get(0).getStatus() == LikeStatusEnum.UNLIKE.getStatus()) {
            if (CommentTypeEnum.COMMENT.getType()==comment.getType()) {
                likesService.add(user, comment);
                commentService.getDianZan(comment.getId());
                Comment commentVo = commentService.getCommentById(comment.getId());
                return Result.okOf(commentVo);
            }else {
                likesService.add(user, comment);
                questionService.dianzan(comment.getId());
                QuestionModel questionModel=questionService.getQuestionById(comment.getId());
                return Result.okOf(questionModel);
            }
        }

         return Result.errorOf(CustomizeErrorCode.REPEAT_THE_THUMB_UP);
    }


    @GetMapping("/likes/{id}")
    public String likes(HttpServletRequest request,@PathVariable(name = "id") Long id){
        User user= (User) request.getSession().getAttribute("user");
        if (user==null){
            return  "redirect:/";
        }
        Likes likes = likesService.getLikeById(id);
        if (CommentTypeEnum.QUESETION.getType()==likes.getType()){
            return "redirect:/question/"+likes.getParentid();
        }else if (CommentTypeEnum.COMMENT.getType()==likes.getType()){
            Comment comment = commentService.getCommentById(likes.getParentid());
            if (CommentTypeEnum.QUESETION.getType()==comment.getType()){
                return "redirect:/question/"+comment.getParentid();
            }
        }

        return "/";
    }


    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST )
    public Result update(@RequestBody Question question, HttpServletRequest request, Model model){
        User user= (User) request.getSession().getAttribute("user");
        if (user==null){
            Result.errorOf(CustomizeErrorCode.REPEAT_THE_THUMB_UP);
        }
        questionService.update(question.getId(),question.getStatus());
        return Result.okOf();
    }

    }


    /*@ResponseBody
    @RequestMapping(value = "/dianzan",method = RequestMethod.POST)
    public Result<Comment> dianzan(@RequestBody Comment comment, HttpServletRequest request, Model model){

        User user= (User) request.getSession().getAttribute("user");
        if (user ==null){
            return  Result.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        commentService.getDianZan(comment.getId());
        Comment commentVo=commentService.getCommentById(comment.getId());
        return Result.okOf(commentVo);
    }*/

