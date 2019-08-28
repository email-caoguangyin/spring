package com.oracle.springboot.mapper;

import com.oracle.springboot.bean.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("INSERT INOT question (title,description,createdate,modifydate,createUser,yuedu,dianzan,pinglun,tag)VALUES(#{title},#{description},#{createdate},#{modifydate},#{createUser},#{yuedu},#{dianzan},#{pinglun},#{tag}) ")
    public void createQuestion(Question question);

}
