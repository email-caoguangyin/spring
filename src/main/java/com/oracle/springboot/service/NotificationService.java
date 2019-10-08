package com.oracle.springboot.service;


import com.oracle.springboot.bean.NotificationModel;
import com.oracle.springboot.bean.QuestionPage;
import com.oracle.springboot.pojo.User;

public interface NotificationService {
    QuestionPage getNotificationPageByUser(Long userid, Integer size, Integer page);

    int unread(User user);

    NotificationModel read(Long id, User user);
}
