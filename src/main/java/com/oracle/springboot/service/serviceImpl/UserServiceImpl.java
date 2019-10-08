package com.oracle.springboot.service.serviceImpl;


import com.oracle.springboot.dao.UserMapper;
import com.oracle.springboot.pojo.User;
import com.oracle.springboot.pojo.UserExample;
import com.oracle.springboot.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /** 根据 ID 获得用户
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Long userId) {
        User user=userMapper.selectByPrimaryKey(userId);
        return user;
    }

    /** 创建或修改用户
     *  根据 ID 判断用户是否存在，然后进行添加或修改
     *
     *
     * @param userPo
     */
    @Override
    public void UserUpdateOrCreate(User userPo) {

       UserExample userExample=new UserExample();
       userExample.createCriteria().andLoginnameEqualTo(userPo.getLoginname());
        List<User> userVo=userMapper.selectByExample(userExample);

        if (userVo.size()==0){
            userMapper.insert(userPo);
        }else {
            userMapper.updateUser(userPo);
        }

    }

    /** 根据 TOKEN 获得用户
     *
     * @param token
     * @return
     */
    @Override
    public List<User> getUserByToken(String token) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andTokenEqualTo(token);
        return userMapper.selectByExample(userExample);
    }

    /** 根据登录名获得用户
     *
     * @param longName
     * @return
     */
    @Override
    public List<User> getUserByLongName(Long longName) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andLoginnameEqualTo(longName);
        return userMapper.selectByExample(userExample);
    }

}
