package com.oracle.springboot.service.serviceImpl;

import com.oracle.springboot.bean.CommentModel2;
import com.oracle.springboot.dao.CommentMapper;
import com.oracle.springboot.dao.NotificationMapper;
import com.oracle.springboot.dao.QuestionMapper;
import com.oracle.springboot.dao.UserMapper;
import com.oracle.springboot.enums.CommentTypeEnum;
import com.oracle.springboot.enums.NotificationStatusEnum;
import com.oracle.springboot.enums.NotificationTypeEnum;
import com.oracle.springboot.exception.CustomizeErrorCode;
import com.oracle.springboot.exception.CustomizeException;
import com.oracle.springboot.pojo.*;
import com.oracle.springboot.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    /** 问题回复 或评论回复
     *
     * @param comment
     */

    @Override
    public void addComment(Comment comment,User user) {
        //判断是否有问题ID
      if (comment.getParentid() ==null || comment.getParentid()==0){
                throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
      //判断是问题的回复还是评论的回复，或者两者都不是
        if (comment.getType() ==null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_WRONG);
        }
        //如何是评论的回复
        if (comment.getType() ==CommentTypeEnum.COMMENT.getType()){
            //回复评论
           Comment dbcomment= commentMapper.getCommentByParentId(comment.getParentid());
           if (dbcomment ==null){
               throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
           }else {
               //回复问题
               Question dbquestion=questionMapper.getQuestionById(dbcomment.getParentid());
               if (dbquestion ==null){
                   throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
               }
               commentMapper.insert(comment);

               //创建通知
               createNotifiy(comment, dbcomment.getCreateuser(),user.getName(),dbquestion.getTitle(),NotificationTypeEnum.REPLY_COMMENT,dbquestion.getId());

           }
        }else {

            //回复问题
            Question dbquestion=questionMapper.getQuestionById(comment.getParentid());
            if (dbquestion ==null){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }else {
                commentMapper.insert(comment);
                questionMapper.updatePingLun(comment);
                //创建通知
                createNotifiy(comment, dbquestion.getCreateuser(),user.getName(),dbquestion.getTitle(),NotificationTypeEnum.REPLY_QUESTION,dbquestion.getId());
            }
        }

    }

    private void createNotifiy(Comment comment, Long jieshou,String notifiername,String questiontitle, NotificationTypeEnum notificationTypeEnum,Long outerId) {
        Notification notification=new Notification();
        notification.setCreatedate(new Date());
        notification.setType(notificationTypeEnum.getType());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());

        notification.setOuterid(outerId);
        notification.setFabu(comment.getCreateuser());
        notification.setJieshou(jieshou);
        notification.setNotifiername(notifiername);
        notification.setQuestiontitle(questiontitle);
        if (notification.getFabu()!=notification.getJieshou()){
            notificationMapper.insert(notification);
        }
    }

    private Notification createNotifiy(Comment comment, Comment dbcomment,Long outerId) {
        Notification notification=new Notification();
        notification.setCreatedate(new Date());
        notification.setType(NotificationTypeEnum.REPLY_COMMENT.getType());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setOuterid(outerId);
        notification.setFabu(comment.getCreateuser());
        notification.setJieshou(dbcomment.getCreateuser());
        return notification;
    }


    @Override
    public List<CommentModel2> getCommentByParentId(Long id) {
        CommentExample commentExample=new CommentExample();
        commentExample.setOrderByClause("createdate desc");
        commentExample.createCriteria().andParentidEqualTo(id).andTypeEqualTo(CommentTypeEnum.QUESETION.getType());
        List<Comment> commentList=commentMapper.selectByExample(commentExample);
        List<CommentModel2> commentModel2List=new ArrayList<>();
        for (Comment comment:commentList) {

            User user=userMapper.selectByPrimaryKey(comment.getCreateuser());
            CommentModel2 commentModel2=new CommentModel2();
            BeanUtils.copyProperties(comment,commentModel2);
            commentModel2.setUser(user);
            commentModel2.setPingluns(commentMapper.getCommentCount(comment.getId()));
            commentModel2List.add(commentModel2);

        }
        return commentModel2List;
    }

    /**  根据TYPE 类型，查看回复
     *
     * @param id
     * @param type
     * @return
     */

    @Override
    public List<CommentModel2> getCommentByParentId(Long id, CommentTypeEnum type) {
        CommentExample commentExample=new CommentExample();
        commentExample.setOrderByClause("createdate desc");
        commentExample.createCriteria()
                .andParentidEqualTo(id)
                .andTypeEqualTo(type.getType());
       List<Comment> commentList=commentMapper.selectByExample(commentExample);
        if (commentList.size()==0){
            return  new ArrayList<>();
        }
        //获取去重的评论人
        Set<Long> createlist=commentList.stream().map(comment ->comment.getCreateuser()).collect(Collectors.toSet());
        List<Long> userIds=new ArrayList<>();
        userIds.addAll(createlist);

        //获取评论人并转化为map
        UserExample userExample=new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> userList=userMapper.selectByExample(userExample);

        Map<Long,User> userMap=userList.stream().collect(Collectors.toMap(user -> user.getId(),user  -> user  ));

        List<CommentModel2> commentModel2List=commentList.stream().map(comment -> {
            CommentModel2 commentModel2=new CommentModel2();
            BeanUtils.copyProperties(comment,commentModel2);
            commentModel2.setUser(userMap.get(comment.getCreateuser()));
            return  commentModel2;
        }).collect(Collectors.toList());

        return commentModel2List;

    }

    @Override
    public void getDianZan(Long id) {

        commentMapper.DianZan(id);
    }

    @Override
    public  Comment  getCommentById(Long id) {
        return commentMapper.selectByPrimaryKey(id);
    }


}
