package com.oracle.springboot.mapper;

import com.oracle.springboot.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("Select * from user where id=#{userId}")
    public User getUserById(Integer userId);

    @Select("INSERT INTO `user` (name,pwd,sex,status)VALUES (name=#{name}, pwd=#{pwd},sex=#{sex},status=#{status}) ")
    public void  addUser(User user);

    @Delete("delete from user where id=#{id}")
    void daleteUserById(Integer id);

    @Select("Select * from user where id=#{loginname}")
    public User getUserByLoginname(Integer loginname);
}
