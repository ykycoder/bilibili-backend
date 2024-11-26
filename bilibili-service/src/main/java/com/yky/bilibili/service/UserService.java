package com.yky.bilibili.service;

import com.yky.bilibili.domain.User;

public interface UserService {

    void addUser(User user);

    User getUserByPhone(String phone);
}
