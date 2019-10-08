package com.oracle.springboot.daos;

import com.oracle.springboot.pojo.Comment;
import com.oracle.springboot.pojo.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface QuestionEssMapper {
    @Insert("INSERT INTO  question (title,description,createdate,modifydate,createUser,yuedu,dianzan,pinglun,tag)VALUES(#{title},#{description},#{createdate},#{modifydate},#{createUser},#{yuedu},#{dianzan},#{pinglun},#{tag}) ")
    public void addQuestion(Question question);

    @Select("SELECT * FROM `question`")
    List<Question> getQuestion();

    @Select("SELECT * FROM `question` WHERE ID=#{questionId}")
    Question getQuestionById(Long questionId);

    @Select("select count(1) from `question`")
    Integer count();

    @Select("select * from `question` limit #{offset},#{size}")
    List<Question> PageList(@Param(value = "offset") Integer offset,@Param(value = "size")  Integer size);

    @Select("select * from `question` where createUser=#{userId} limit #{offset},#{size} ")
    List<Question> PageListByUser(@PathVariable(value = "userId")Long userId,@Param(value = "offset") Integer offset,@Param(value = "size")  Integer size);

    @Select("select count(1) from `question` where createUser=#{userId}")
    Integer countByUser(@PathVariable(value = "userId")Long userId);

    @Update("update `question`  set title=#{title},description=#{description},tag=#{tag},createdate=#{createdate} where id=#{id}")
    void updateQuestion(Question questionVo);

    @Update("update `question`  SET YUEDU=YUEDU+1 where id=#{id}")
    void updateYueDu(Long id);

    @Update("update `question`  SET PINGLUN=PINGLUN+1 where id=#{parentId}")
    void updatePingLun(Comment comment);
}
