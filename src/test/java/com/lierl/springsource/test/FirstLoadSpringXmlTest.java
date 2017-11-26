package com.lierl.springsource.test;

import com.lierl.springsource.controller.UserController;
import com.lierl.springsource.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lierl
 * 2017/11/26 9:50.
 */
public class FirstLoadSpringXmlTest {

    private static final Logger logger = LoggerFactory.getLogger(FirstLoadSpringXmlTest.class);
    @Test
    public void first(){
        ApplicationContext contest = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) contest.getBean("userService");
        userService.first("hello world!!!");
        UserController userController = (UserController) contest.getBean("userController");
        userController.query();
        logger.info("test--------");
    }
}
