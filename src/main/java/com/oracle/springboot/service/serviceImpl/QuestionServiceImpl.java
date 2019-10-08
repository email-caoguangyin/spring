package com.oracle.springboot.service.serviceImpl;



import com.oracle.springboot.bean.QuestionPage;
import com.oracle.springboot.bean.QuestionModel;
import com.oracle.springboot.dao.QuestionMapper;
import com.oracle.springboot.dao.UserMapper;
import com.oracle.springboot.pojo.Question;
import com.oracle.springboot.pojo.QuestionExample;
import com.oracle.springboot.pojo.User;
import com.oracle.springboot.pojo.UserExample;
import com.oracle.springboot.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    /**  获取所有的问题
     *
     * @return
     */
    @Override
    public List<QuestionModel> getQuestion() {
        List<Question> list=questionMapper.selectByExample(new QuestionExample());
        List<QuestionModel> questionModelList=new ArrayList<QuestionModel>();
        for (Question question:list){
            UserExample userExample=new UserExample();
            userExample.createCriteria().andIdEqualTo(question.getCreateuser());
            List<User> userList=userMapper.selectByExample(userExample);
            QuestionModel questionModel=new QuestionModel();
     BeanUtils.copyProperties(question,questionModel);
     questionModel.setUser(userList.get(0));
     questionModel.setName(userList.get(0).getName());
     questionModelList.add(questionModel);

     }
     return questionModelList;
     }

     /** 增加问题
     *
     * @param question
     */

    @Override
    public void addQuestion(Question question) {
        questionMapper.insert(question);
    }

    /**  根据ID获取问题
     *
     * @param questionId
     * @return
     */
    @Override
    public  QuestionModel getQuestionById(Long questionId) {
        Question question=questionMapper.selectByPrimaryKey(questionId);
        QuestionModel questionModel=new QuestionModel();
        BeanUtils.copyProperties(question,questionModel);
        UserExample userExample=new UserExample();
        userExample.createCriteria().andIdEqualTo(question.getCreateuser());
        List<User> userList=userMapper.selectByExample(userExample);
        questionModel.setUser(userList.get(0));
        return questionModel;
    }

    /** 分页显示所有的问题
     *
     * @param page
     * @param size
     * @return
     */

    @Override
    public QuestionPage getQuestionPage(Integer page, Integer size) {
        QuestionPage questionPage=new QuestionPage();
        Integer totalCount=questionMapper.count();
        //Integer totalCount=questionEssMapper.count();
        questionPage.setPagination(totalCount,page,size);
        if (page < 1){
            page = 1;
        }
        if (page > questionPage.getTotalPage()){
            page =questionPage.getTotalPage();
        }

        Integer offset=size * (page - 1);
        List<Question> list=questionMapper.PageList(offset,size);
        //List<Question> list=questionEssMapper.PageList(offset,size);

        List<QuestionModel> questionModelList=new ArrayList<QuestionModel>();
        for (Question question:list){
            //获取用户
            UserExample userExample=new UserExample();
            userExample.createCriteria().andIdEqualTo(question.getCreateuser());
            List<User> userList=userMapper.selectByExample(userExample);

            QuestionModel questionModel=new QuestionModel();
            BeanUtils.copyProperties(question,questionModel);
            questionModel.setUser(userList.get(0));
            questionModel.setName(userList.get(0).getName());

            String descriptionStr=questionModel.getDescription();
            if (descriptionStr.length()>100){
                descriptionStr=descriptionStr.substring(0,100)+"……";
            }
            questionModel.setDescription(descriptionStr);

            questionModelList.add(questionModel);

        }
        questionPage.setData(questionModelList);
        return questionPage;
    }

    /** 分页显示自己的问题
     *
     * @param userId
     * @param size
     * @param page
     * @return
     */

    @Override
    public  QuestionPage getQuestionPageByUser(Long userId, Integer size, Integer page) {
        QuestionPage questionPage=new QuestionPage();
        Integer totalCount=questionMapper.countByUser(userId);
        //Integer totalCount=questionEssMapper.countByUser(userId);
        questionPage.setPagination(totalCount,page,size);
        if (page < 1){
            page = 1;
        }
        if (page > questionPage.getTotalPage()){
            page =questionPage.getTotalPage();
        }

        Integer offset=size * (page - 1);
        List<Question> list=questionMapper.PageListByUser(userId,offset,size);
        //List<Question> list=questionEssMapper.PageListByUser(userId,offset,size);

        List<QuestionModel> questionModelList=new ArrayList<QuestionModel>();
        for (Question question:list){

            UserExample userExample=new UserExample();
            userExample.createCriteria().andIdEqualTo(question.getCreateuser());
            List<User> userList=userMapper.selectByExample(userExample);
            QuestionModel questionModel=new QuestionModel();
            BeanUtils.copyProperties(question,questionModel);
            questionModel.setUser(userList.get(0));
            questionModel.setName(userList.get(0).getName());
            questionModelList.add(questionModel);

        }
        questionPage.setData(questionModelList);
        return questionPage;
    }

    /** 查看自己的问题个数
     *
     * @param id
     * @return
     */

    @Override
    public Integer getCountByUser(Long id) {
        return questionMapper.countByUser(id);

    }

    /** 修改问题
     *
     * @param question
     */

    @Override
    public void UpdateQuestion(Question question) {
        Question questionVo=questionMapper.selectByPrimaryKey(question.getId());
        //如果数据库有数据，就调用修改方法
        questionVo.setTitle(question.getTitle());
        questionVo.setDescription(question.getDescription());
        questionVo.setTag(question.getTag());
        questionVo.setModifydate(new Date());


        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(questionVo.getId());
        questionMapper.updateByExampleSelective(questionVo, questionExample);
    }

    /** 增加阅读数
     *
     * @param id
     */

    @Override
    public void updateYueDu(Long id) {
        questionMapper.updateYueDu(id);
        //questionEssMapper.updateYueDu(id);

    }

    /** 查看问题的标签
     *
     * @param questionModel
     * @return
     */
    @Override
    public List<QuestionModel> selectTag(QuestionModel questionModel) {
        if (StringUtils.isBlank(questionModel.getTag())){
            return new ArrayList<>();
        }
        String[] tags= StringUtils.split(questionModel.getTag(),",");
        String regexpTag= Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question=new Question();
        question.setId(questionModel.getId());
        question.setTag(regexpTag);
        List<Question> questionList=questionMapper.selectTag(question);
        List<QuestionModel> questionModels = questionList.stream().map(q -> {
            QuestionModel questionModel1 = new QuestionModel();
            BeanUtils.copyProperties(q,questionModel1);
            return questionModel1;
        }).collect(Collectors.toList());


        return questionModels;
    }
}
