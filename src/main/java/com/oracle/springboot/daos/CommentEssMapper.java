package com.oracle.springboot.daos;

import com.oracle.springboot.bean.CommentModel2;
import com.oracle.springboot.pojo.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface CommentEssMapper {
    @Insert("INSERT INTO `comment` (content,type,parentId,createdate,modifydate,dianzan,constraint) VALUES(#{content},#{type},#{parentId},#{createdate},#{modifydate},#{dianzan},#{constraint})")
    public void addComment(Comment comment) ;

    @Select("Select * from `comment` where parentId=#{parentId}")
    Comment getCommentByParentId(Long id);

    @Select("Select * from `comment` where parentId=#{parentId} and type=1")
    List<CommentModel2> getCommentByParentIdByQuestion(Long id, int i);
}





