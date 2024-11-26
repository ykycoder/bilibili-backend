package com.yky.bilibili.controller;

import com.yky.bilibili.domain.JsonResponse;
import com.yky.bilibili.domain.User;
import com.yky.bilibili.service.UserService;
import com.yky.bilibili.service.util.RSAUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublicKey() {
        String pk = RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(pk);
    }

    @PostMapping("/users")
    public JsonResponse<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return JsonResponse.success();
    }
}
