package com.lierl.springsource.test;

import com.lierl.springsource.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lierl
 * 2017/11/26 9:50.
 */
public class FirstLoadSpringXmlTest {

    public static Map<String,String> data = new HashMap<String,String>();

    private static final Logger logger = LoggerFactory.getLogger(FirstLoadSpringXmlTest.class);
    @Test
    public void first(){
        ApplicationContext contest = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) contest.getBean("userService");
        userService.first("hello world!!!");
//        UserController userController = (UserController) contest.getBean("userController");
//        userController.query();
       // AspceJAdvice advice = (AspceJAdvice) contest.getBean("aspectJAdvice");
       // logger.info("test--------" + advice);

        String[] names = contest.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name+"、");
        }

        Object obj = contest.getBean("org.springframework.aop.config.internalAutoProxyCreator");
        System.out.println(obj.getClass());
    }
}
