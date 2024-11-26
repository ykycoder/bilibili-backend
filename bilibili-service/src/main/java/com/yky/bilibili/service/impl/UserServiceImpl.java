package com.yky.bilibili.service.impl;

import com.yky.bilibili.domain.User;
import com.yky.bilibili.domain.UserInfo;
import com.yky.bilibili.domain.constant.UserConstant;
import com.yky.bilibili.domain.exception.ConditionException;
import com.yky.bilibili.mapper.UserMapper;
import com.yky.bilibili.service.UserService;
import com.yky.bilibili.service.util.MD5Util;
import com.yky.bilibili.service.util.RSAUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        String phone = user.getPhone();
        if (StringUtils.isBlank(phone)) {
            throw new ConditionException("手机号不能为空！");
        }
        User dbUser = this.getUserByPhone(phone);
        if (dbUser != null) {
            throw new ConditionException("该手机号已经注册！");
        }
        Date now = new Date();
        String salt = String.valueOf(now.getTime());
        String password = user.getPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("密码解密失败！");
        }
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        user.setSalt(salt);
        user.setPassword(md5Password);
        user.setCreateTime(now);
        userMapper.addUser(user);
        // 添加用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNick(UserConstant.DEFAULT_NICK);
        userInfo.setBirth(UserConstant.DEFAULT_BIRTH);
        userInfo.setGender(UserConstant.GENDER_MALE);
        userInfo.setCreateTime(now);
        userMapper.addUserInfo(userInfo);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }
}
