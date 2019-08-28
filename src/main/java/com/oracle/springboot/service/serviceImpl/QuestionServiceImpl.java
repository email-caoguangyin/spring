package com.oracle.springboot.service.serviceImpl;

import com.oracle.springboot.bean.Question;
import com.oracle.springboot.mapper.QuestionMapper;
import com.oracle.springboot.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionMapper;
    @Override
    public Question getQuestion() {
        return null;
    }

    @Override
    public void addQuestion(Question question) {
        questionMapper.addQuestion(question);
    }
}
