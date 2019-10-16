package com.oracle.springboot.service.serviceImpl;

import com.oracle.springboot.bean.LikesModel;
import com.oracle.springboot.bean.QuestionPage;
import com.oracle.springboot.dao.*;
import com.oracle.springboot.enums.CommentTypeEnum;
import com.oracle.springboot.enums.LikeStatusEnum;
import com.oracle.springboot.enums.NotificationTypeEnum;
import com.oracle.springboot.pojo.*;
import com.oracle.springboot.service.LikesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LikesServiceImpl implements LikesService {
    @Autowired
    private LikesMapper likesMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;


    @Override
    public void add(User user, Comment comment) {
        Likes likes=new Likes();
        likes.setStatus(LikeStatusEnum.LIKE.getStatus());
        likes.setUser(user.getId());
        likes.setParentid(comment.getId());
        likes.setType(comment.getType());
        likes.setCreatedate(new Date());
        likesMapper.insert(likes);
        if (likes.getType()== CommentTypeEnum.QUESETION.getType()){
            Notification notification=new Notification();
            notification.setStatus(0);
            notification.setFabu(user.getId());
            Question question=questionMapper.selectByPrimaryKey(comment.getId());
            notification.setJieshou(question.getCreateuser());
            notification.setOuterid(question.getId());
            notification.setType(NotificationTypeEnum.LIKE_QUESTION.getType());
            notification.setCreatedate(new Date());
            notification.setStatus(LikeStatusEnum.UNLIKE.getStatus());
            User userVo=userMapper.selectByPrimaryKey(user.getId());
            notification.setNotifiername(userVo.getName());
            notification.setQuestiontitle(question.getTitle());
            if (notification.getFabu()!=notification.getJieshou()){
                notificationMapper.insert(notification);
            }

        }else if (likes.getType()== CommentTypeEnum.COMMENT.getType()){
            Notification notification=new Notification();
            notification.setStatus(LikeStatusEnum.UNLIKE.getStatus());
            notification.setFabu(user.getId());
            Comment commentVo= commentMapper.selectByPrimaryKey(comment.getId());
            notification.setJieshou(commentVo.getCreateuser());
            notification.setOuterid(comment.getId());
            notification.setType(NotificationTypeEnum.LIKE_COMMENT.getType());
            notification.setCreatedate(new Date());
            User userVo=userMapper.selectByPrimaryKey(user.getId());
            notification.setNotifiername(userVo.getName());
            notification.setQuestiontitle(commentVo.getContent());
            if (notification.getFabu()!=notification.getJieshou()){
                notificationMapper.insert(notification);
            }

        }
    }

    @Override
    public List<Likes> getLike(User user, Comment comment) {
        LikesExample likesExample = new LikesExample();
        likesExample.createCriteria().andUserEqualTo(user.getId()).andParentidEqualTo(comment.getId()).andTypeEqualTo(comment.getType());
        return likesMapper.selectByExample(likesExample);

    }

    @Override
    public QuestionPage getLikesPageByUser(Long userId, Integer size, Integer page) {
        LikesExample likesExample=new LikesExample();
        likesExample.createCriteria().andUserEqualTo(userId);
        List<Likes> likesList=likesMapper.selectByExample(likesExample);
        if (likesList.size()==0) {
            QuestionPage questionPages = new QuestionPage();
            return questionPages;
        }
        QuestionPage<LikesModel> questionPage=new QuestionPage<LikesModel>();
        LikesExample likesExamples = new LikesExample();
        likesExamples.createCriteria().andUserEqualTo(userId);
        Integer totalCount= likesMapper.countByExample(likesExamples);
        questionPage.setPagination(totalCount,page,size);
        if (page < 1){
            page = 1;
        }
        if (page > questionPage.getTotalPage()){
            page =questionPage.getTotalPage();
        }

        Integer offset=size * (page - 1);

            List<Likes> list = likesMapper.getLikesPageByUser(userId, offset, size);


            if (list.size() == 0) {
                return questionPage;
            }
            List<LikesModel> likesModelList = new ArrayList<LikesModel>();
            for (Likes likes : list) {
                LikesModel likesModel = new LikesModel();
                BeanUtils.copyProperties(likes, likesModel);
                if (likes.getType().equals(CommentTypeEnum.QUESETION.getType())){
                    Question question=questionMapper.selectByPrimaryKey(likes.getParentid());
                    String title=question.getTitle();
                    if (title.length()>10){
                        title=title.substring(0,10)+"……";
                    }
                    likesModel.setTitle(title);
                    likesModel.setOuterTitle(NotificationTypeEnum.LIKE_QUESTION.getName());
                }else  if (likes.getType().equals(CommentTypeEnum.COMMENT.getType())){
                    Comment comment=commentMapper.selectByPrimaryKey(likes.getParentid());
                    String title=comment.getContent();
                    if (title.length()>10){
                        title=title.substring(0,10)+"……";
                    }
                    likesModel.setTitle(title);
                    likesModel.setOuterTitle(NotificationTypeEnum.LIKE_COMMENT.getName());
                }

                likesModelList.add(likesModel);
            }
            questionPage.setData(likesModelList);
            return questionPage;

    }

    @Override
    public Integer getCountByUser(User user) {
        LikesExample likesExample = new LikesExample();
        likesExample.createCriteria().andUserEqualTo(user.getId());
        Integer count= likesMapper.countByExample(likesExample);
        return count;
    }

    @Override
    public Likes getLikeById(Long id) {
        return likesMapper.selectByPrimaryKey(id);
    }


}
