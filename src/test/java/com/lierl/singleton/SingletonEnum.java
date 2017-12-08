package com.lierl.singleton;

/**
 * @author lierlei@xingyoucai.com
 * @create 2017-12-06 15:18
 **/
public enum SingletonEnum {
	INSTANCE;
	private Singleton singleton = null;
	SingletonEnum(){
		singleton = new Singleton();
	}

	public Singleton getInstance(){
		return singleton;
	}

}
