package com.oracle.springboot.service.serviceImpl;

import com.oracle.springboot.bean.NotificationModel;
import com.oracle.springboot.bean.QuestionPage;
import com.oracle.springboot.bean.QuestionQueryDto;
import com.oracle.springboot.dao.NotificationMapper;
import com.oracle.springboot.dao.UserMapper;
import com.oracle.springboot.enums.NotificationStatusEnum;
import com.oracle.springboot.enums.NotificationTypeEnum;
import com.oracle.springboot.exception.CustomizeErrorCode;
import com.oracle.springboot.exception.CustomizeException;
import com.oracle.springboot.pojo.*;
import com.oracle.springboot.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;

    /** 回复的分页
     *
     * @param userId
     * @param size
     * @param page
     * @return
     */
    @Override
    public QuestionPage getNotificationPageByUser(Long userId, Integer size, Integer page) {

        NotificationExample notificationExampleVO = new NotificationExample();
        notificationExampleVO.createCriteria().andJieshouEqualTo(userId);
        List<Notification> notificationList = notificationMapper.selectByExample(notificationExampleVO);
        if (notificationList.size()==0){
            return new QuestionPage();
        }
        QuestionPage<NotificationModel> questionPage=new QuestionPage<NotificationModel>();

        NotificationExample notificationExample=new NotificationExample();
        notificationExample.createCriteria().andJieshouEqualTo(userId);
        Integer totalCount= (int) notificationMapper.countByExample(notificationExample);
        questionPage.setPagination(totalCount,page,size);
        if (page < 1){
            page = 1;
        }
        if (page > questionPage.getTotalPage()){
            page =questionPage.getTotalPage();
        }

        Integer offset=size * (page - 1);


            List<Notification> list = notificationMapper.PageListByUser(userId, offset, size);


            if (list.size() == 0) {
                return questionPage;
            }
            List<NotificationModel> notificationModelList = new ArrayList<NotificationModel>();
            for (Notification notification : list) {
                NotificationModel notificationModel = new NotificationModel();
                BeanUtils.copyProperties(notification, notificationModel);
                notificationModel.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
                notificationModel.setOuterTitle(notification.getQuestiontitle());
                String  questiontitle=notification.getQuestiontitle();
                if (questiontitle.length()>10){
                    questiontitle=questiontitle.substring(0,10)+"……";
                }
                notificationModel.setOuterTitle(questiontitle);
                notificationModelList.add(notificationModel);

            }
            questionPage.setData(notificationModelList);
            return questionPage;

    }

    /** 未读的条数
     *
     * @param user
     * @return
     */
    @Override
    public int unread(User user) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample
                .createCriteria()
                .andJieshouEqualTo(user.getId())
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());

        return notificationMapper.countByExample(notificationExample);
    }

    /** 最新回复
     *
     * @param id
     * @param user
     * @return
     */
    @Override
    public NotificationModel read(Long id, User user) {

        Notification notification=notificationMapper.selectByPrimaryKey(id);
        if (notification==null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getJieshou(),user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationModel notificationModel = new NotificationModel();
        BeanUtils.copyProperties(notification,notificationModel);
        notificationModel.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationModel;
    }

    @Override
    public List<Notification> selectDoomsd(QuestionQueryDto questionQueryDto) {
        List<Notification> notificationList= notificationMapper.selectDoomsd(questionQueryDto);
        return notificationList;
    }
}
