package com.oracle.springboot.service;

import com.oracle.springboot.bean.Question;

public interface QuestionService {

    public Question getQuestion();

    public void  addQuestion(Question question);
}
