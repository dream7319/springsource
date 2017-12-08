package com.lierl.springsource.proxy;

import com.lierl.springsource.service.UserService;
import com.lierl.springsource.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lierlei@xingyoucai.com
 * @create 2017-11-27 15:03
 **/
public class JDKProxy implements InvocationHandler {

	public Object target;

	public JDKProxy(Object target){
		this.target = target;
	}

	public <T> T getProxy(){
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("前置通知");
		Object invoke = method.invoke(target, args);
		System.out.println("后置通知");
		return invoke;
	}

	public static void main(String[] args) {
		JDKProxy proxy = new JDKProxy(new UserServiceImpl());
		UserService userService = (UserService) proxy.getProxy();
		userService.first("------hello world");
	}
}
