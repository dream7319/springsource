package com.lierl.springsource.test;

import com.lierl.singleton.Singleton;
import com.lierl.singleton.SingletonEnum;

/**
 * @author lierlei@xingyoucai.com
 * @create 2017-12-01 15:45
 **/
public class ThreadTest {
	public static void main(String[] args) {
		Singleton instance = SingletonEnum.INSTANCE.getInstance();
		instance.test();
	}
}
