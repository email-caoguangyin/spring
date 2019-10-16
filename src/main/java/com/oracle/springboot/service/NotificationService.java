package com.oracle.springboot.service;


import com.oracle.springboot.bean.NotificationModel;
import com.oracle.springboot.bean.QuestionPage;
import com.oracle.springboot.bean.QuestionQueryDto;
import com.oracle.springboot.pojo.Notification;
import com.oracle.springboot.pojo.User;

import java.util.List;

public interface NotificationService {
    QuestionPage getNotificationPageByUser(Long userid, Integer size, Integer page);

    int unread(User user);

    NotificationModel read(Long id, User user);

    List<Notification> selectDoomsd(QuestionQueryDto questionQueryDto);
}
