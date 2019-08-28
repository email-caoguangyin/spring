package com.oracle.springboot.service.serviceImpl;

import com.oracle.springboot.bean.User;
import com.oracle.springboot.mapper.UserMapper;
import com.oracle.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer userId) {
    User user=userMapper.getUserById(userId);
        return user;
    }

}
