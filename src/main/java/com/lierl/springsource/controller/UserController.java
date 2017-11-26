package com.lierl.springsource.controller;

import com.lierl.springsource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by lierl
 * 2017/11/26 12:04.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

//    @RequestMapping(value="query")
    public void query(){
        userService.first("hello world");
    }
}
