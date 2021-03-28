package com.nandu.service;

import com.nandu.pojo.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User checkUser(String username,String password);
}
