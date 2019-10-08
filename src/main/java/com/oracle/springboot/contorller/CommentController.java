package com.oracle.springboot.contorller;

import com.oracle.springboot.bean.CommentModel2;
import com.oracle.springboot.enums.CommentTypeEnum;
import com.oracle.springboot.exception.CustomizeErrorCode;
import com.oracle.springboot.pojo.Comment;
import com.oracle.springboot.pojo.User;
import com.oracle.springboot.service.CommentService;
import com.oracle.springboot.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody Comment comment, HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        if (user ==null){
            return  Result.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        comment.setCreateuser(user.getId());
        comment.setCreatedate(new Date());
        comment.setDianzan(0);
        commentService.addComment(comment,user);
         return Result.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public Result<List<CommentModel2>> comments(@PathVariable(name = "id") Long id){
       List<CommentModel2> commentModel2List= commentService.getCommentByParentId(id , CommentTypeEnum.COMMENT);
        return Result.okOf(commentModel2List);
    }

    @ResponseBody
    @RequestMapping(value = "/dianzan",method = RequestMethod.POST)
    public Result<Comment> dianzan(@RequestBody Comment comment, HttpServletRequest request, Model model){

        User user= (User) request.getSession().getAttribute("user");
        if (user ==null){
            return  Result.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        commentService.getDianZan(comment.getId());
        Comment commentVo=commentService.getCommentById(comment.getId());
        return Result.okOf(commentVo);
    }
}
