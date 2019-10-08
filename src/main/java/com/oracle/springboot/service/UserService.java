package com.oracle.springboot.service;

import com.oracle.springboot.pojo.User;

import java.util.List;

public interface UserService {

    User getUserById(Long userId);

    List<User> getUserByToken(String token);

    List<User> getUserByLongName(Long createUser);

    void UserUpdateOrCreate(User user);
}
