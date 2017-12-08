package com.lierl.springsource.controller;

import com.lierl.springsource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lierl
 * 2017/11/26 12:04.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam String name){
        System.out.println(name);
        userService.first("hello world");
        return name;
    }
}
