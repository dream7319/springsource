package com.lierl.springsource.proxy;

import com.lierl.springsource.service.UserService;
import com.lierl.springsource.service.impl.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lierlei@xingyoucai.com
 * @create 2017-11-27 15:09
 **/
public class CGLibProxy implements MethodInterceptor {
	private static CGLibProxy instance = new CGLibProxy();
	private CGLibProxy(){}

	public static CGLibProxy getInstance(){
		return instance;
	}

	public <T> T getProxy(Class<T> cls){
		return (T) Enhancer.create(cls,this);
	}

	@Override
	public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("调用的方法是：" + method.getName());
		System.out.println("实际调用者是： " + object.getClass());
		for (Object obj : objects) {
			System.out.println("方法参数类型为：" + obj.getClass());
		}
		Object result = methodProxy.invokeSuper(object,objects);
		System.out.println("这是方法后");
		return result;
	}

	public static void main(String[] args) {
//		UserService userService = CGLibProxy.getInstance().getProxy(UserService.class);
//		userService.first("hello world!!!");
	}
}
