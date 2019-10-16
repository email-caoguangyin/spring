package com.oracle.springboot.service;

import com.oracle.springboot.bean.QuestionPage;
import com.oracle.springboot.pojo.Comment;
import com.oracle.springboot.pojo.Likes;
import com.oracle.springboot.pojo.User;

import java.util.List;

public interface LikesService {

    void add(User user, Comment comment);

    List<Likes> getLike(User user, Comment comment);

    QuestionPage getLikesPageByUser(Long userId, Integer size, Integer page);

    Integer getCountByUser(User user);

    Likes getLikeById(Long id);
}
