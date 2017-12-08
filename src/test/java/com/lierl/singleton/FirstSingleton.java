package com.lierl.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lierlei@xingyoucai.com
 * @create 2017-12-06 14:46
 *
 **/
public class FirstSingleton {

	private static FirstSingleton singleton = null; //单例对象

	private FirstSingleton(){}//私有构造方法

	/**
	 * 静态工厂方法
	 * 此种方法的弊端： 非线程安全
	 * @return
	 */
	public static FirstSingleton getInstance1(){
		if(singleton == null){//当A、B线程同时执行到这里时，singleton==null，此时会创建两个对象
			singleton = new FirstSingleton();
		}
		return singleton;
	}

	/**
	 * 此方法也不是绝对的线程安全
	 * 问题：此问题涉及到JVM编译器的指定重排
	 * singleton = new FirstSingleton();
	 * 会被编译为如下JVM指令：
	 * 	memory=allocate(); //1 分配对象的内存空间
	 * 	ctorInstance(memory);//2 初始化对象
	 * 	instance=memory;//3:设置instance指向刚分配的内存地址
	 * 	这样的指令有可能改变
	 * 	变为：
	 * 	memory=allocate(); //1 分配对象的内存空间
	 * 	instance=memory;//3:设置instance指向刚分配的内存地址
	 * 	ctorInstance(memory);//2 初始化对象
	 *  当A线程执行完1、3时，singleton未完成初始化,但singleton已经不指向null
	 *  而此时如果线程B抢先了CPU资源,则获取的singleton就为null
	 *  如何避免此中情况呢？可以在singleton前加上volatile
	 *  private volatile static FirstSingleton singleton = null;
	 *
	 *  volatile可以始终保证JVM执行顺序1,2,3
	 * @return
	 */
	public static FirstSingleton getInstance2(){
		if(singleton == null){//双重检测机制
			synchronized (FirstSingleton.class){//同步锁
				if(singleton == null){//双重检测机制
					singleton = new FirstSingleton();
				}
			}
		}
		return singleton;
	}

	/************************静态内部类************************************************************/
	private static class LazyHolder{
		private static final FirstSingleton FIRST_SINGLETON = new FirstSingleton();
	}
	//私有构造方法略

	/**
	 * 注意的点：
	 * 1.从外部无法访问静态内部类LazyHolder，只有当调用Singleton.getInstance方法的时候，
	 *	才能得到单例对象INSTANCE。
	 * 2.INSTANCE对象初始化的时机并不是在单例类Singleton被加载的时候，而是在调用getInstance方法，
	 * 使得静态内部类LazyHolder被加载的时候。因此这种实现方式是利用classloader的加载机制来实现懒加载，
	 * 并保证构建单例的线程安全。
	 *
	 * @return
	 */
	public static FirstSingleton getInstance3(){
		return LazyHolder.FIRST_SINGLETON;
	}

	//以上方法都存在一个问题：无法防止利用反射来重复构建对象

	//通过反射获取对象
	public void getSingletonByReflect(){
		try {
			//获得构造器
			Constructor con = FirstSingleton.class.getDeclaredConstructor();//设置为可访问
			con.setAccessible(true);//构造两个不同的对象
			FirstSingleton singleton1 = (FirstSingleton)con.newInstance();
			FirstSingleton singleton2 = (FirstSingleton)con.newInstance();//验证是否是不同对象
			System.out.println(singleton1.equals(singleton2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**************************通过枚举类实现单例**************************************************/


	/**
	 * 1. volatile关键字不但可以防止指令重排，也可以保证线程访问的变量值是主内存中的最新值。
	   2.使用枚举实现的单例模式，不但可以防止利用反射强行构建单例对象，而且可以在枚举类对象被反序列化的时候，
	     保证反序列的返回结果是同一对象。

	 	/-----------------------------------------------------/
	 	| 单例模式实现 | 是否线程安全 | 是否懒加载 | 是否防止反射构建 |
	 	------------------------------------------------------
	 	| 双重锁检测   |    是      |    是     |      否       |
	 	------------------------------------------------------
	 	| 静态内部类   |    是      |    是     |      否       |
	 	------------------------------------------------------
	 	|    枚举     |    是      |    否     |      是       |
	 	------------------------------------------------------
	 */

}
