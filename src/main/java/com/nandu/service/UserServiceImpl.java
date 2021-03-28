package com.nandu.service;

import com.nandu.mapper.UserMapper;
import com.nandu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        return userMapper.findUser(username,password);
    }

}
