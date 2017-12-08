package com.lierl.springsource.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.core.ResolvableType;

/**
 * @author lierlei@xingyoucai.com
 * @create 2017-11-28 16:51
 **/
public interface BeanFactory {


	//这里是对FactoryBean的转义定义，因为如果使用bean的名字检索FactoryBean得到的对象是工厂生成的对象
	//如果需要得到工厂本身，需要转义
	String FACTORY_BEAN_PREFIX = "&";


	//这里根据bean的名字，在IOC容器中得到bean实例，这个IOC容器就是一个大的抽象工厂。
	Object getBean(String name) throws BeansException;

	//这里根据bean的名字和Class类型来得到bean实例，和上面的方法不同在于它会抛出异常：
	// 如果根据名字取得的bean实例的Class类型和需要的不同的话。
	<T> T getBean(String name, Class<T> requiredType) throws BeansException;

	//根据bean的名称， 可以指定构造函数、工厂方法参数来覆盖more参数获取bean
	Object getBean(String name, Object... args) throws BeansException;

	//返回bean的实力根据bean的Class类型
	<T> T getBean(Class<T> requiredType) throws BeansException;


	//根据bean的class类型， 可以指定构造函数、工厂方法参数来覆盖more参数获取bean
	<T> T getBean(Class<T> requiredType, Object... args) throws BeansException;


	//这里提供对bean的检索，看看是否在IOC容器有这个名字的bean
	boolean containsBean(String name);

	//这里根据bean名字得到bean实例，并同时判断这个bean是不是单例
	boolean isSingleton(String name) throws NoSuchBeanDefinitionException;

	//这里根据bean名字得到bean实例，并同时判断这个bean是不是原型
	boolean isPrototype(String name) throws NoSuchBeanDefinitionException;


	//根据bean的名称和指定的类型是否匹配(根据beanName获取bean实例，然后判断bean实例是否和给定的类型匹配)
	boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException;


	boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException;

	//这里对得到bean实例的Class类型
	Class<?> getType(String name) throws NoSuchBeanDefinitionException;

	//这里得到bean的别名，如果根据别名检索，那么其原名也会被检索出来
	String[] getAliases(String name);

}
