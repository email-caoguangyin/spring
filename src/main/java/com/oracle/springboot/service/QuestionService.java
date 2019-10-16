package com.oracle.springboot.service;

        import com.oracle.springboot.bean.QuestionPage;
        import com.oracle.springboot.bean.QuestionModel;
        import com.oracle.springboot.pojo.Question;

        import java.util.List;

public interface QuestionService {

    public List<QuestionModel> getQuestion();

    public void  addQuestion(Question question);

    QuestionModel getQuestionById(Long questionId);

    QuestionPage getQuestionPage(Integer page , Integer size,String search);

    QuestionPage getQuestionPageByUser(Long id, Integer size, Integer page,Integer status);

    Integer getCountByUser(Long id,Integer status);

    void UpdateQuestion(Question question);

    void updateYueDu(Long id);


    List<QuestionModel> selectTag(QuestionModel questionModel);

    void dianzan(Long id);

    void update(Long id,Integer status);
}