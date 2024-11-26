package com.yky.bilibili.mapper;

import com.yky.bilibili.domain.User;
import com.yky.bilibili.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getUserByPhone(String phone);

    Integer addUser(User user);

    Integer addUserInfo(UserInfo userInfo);
}
