package com.oracle.springboot.service;

import com.oracle.springboot.bean.CommentModel2;
import com.oracle.springboot.enums.CommentTypeEnum;
import com.oracle.springboot.pojo.Comment;
import com.oracle.springboot.pojo.User;

import java.util.List;

public interface CommentService {
    public void addComment(Comment comment, User user);

    List<CommentModel2> getCommentByParentId(Long id);

    List<CommentModel2> getCommentByParentId(Long id, CommentTypeEnum comment);


    void getDianZan(Long id);

    Comment getCommentById(Long id);
}
