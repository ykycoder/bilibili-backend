package com.yky.bilibili.controller;

import com.yky.bilibili.service.BilibiliService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BilibiliController {

    @Resource
    private BilibiliService bilibiliService;
}
