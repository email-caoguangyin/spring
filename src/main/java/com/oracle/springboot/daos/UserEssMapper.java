package com.oracle.springboot.daos;

import com.oracle.springboot.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserEssMapper {

    @Select("Select * from user where id=#{userId}")
    public User getUserById(Long userId);

    @Insert("INSERT INTO `user` (name,loginname,image,dianzan,token)VALUES (#{name},#{loginname},#{image},#{dianzan},#{token}) ")
    public void  addUser(User user);

    @Delete("delete from `user` where id=#{id}")
    void daleteUserById(Long id);

    @Select("SELECT * FROM `user`where loginname=#{loginname}")
    public User getUserByLoginname(Long loginname);

    @Select("SELECT * FROM `user` WHERE TOKEN=#{token}")
    User getUserByToken(String token);

    @Update("UPDATE  `user` SET name=#{name},image=#{image},dianzan=#{dianzan},token=#{token} where loginname=#{loginname}")
    void updateUser(User user);

   @Select({
            "<script>",
            "select",
            "id, name, longinname",
            "from `user`",
            "where id in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<User> getUserByIds(List<Long> ids);
}
