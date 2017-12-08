package com.lierl.springsource.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author lierlei@xingyoucai.com
 * @create 2017-11-27 10:06
 **/
public class AopInterceptor {

	public void doBefore(JoinPoint joinPoint){
		System.out.println("---doBefore().invoke--");
		System.out.println(" 此处意在执行核心业务逻辑前，做一些安全性的判断等等");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("-----End of doBefore()------");
		System.out.println("********************************************************************");
	}

	public Object doAround(ProceedingJoinPoint pjp){
		System.out.println("-----aroundAdvice().invoke-----");
		System.out.println(" 此处可以做类似于Before Advice的事情");
		//调用核心逻辑
		Object retVal = null;
		try {
			retVal = pjp.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		System.out.println(" 此处可以做类似于After Advice的事情");
		System.out.println("-----End of aroundAdvice()------");
		System.out.println("********************************************************************");
		return retVal;
	}

	public void doAfter(){
		System.out.println("-----afterAdvice().invoke-----");
		System.out.println(" 此处意在执行核心业务逻辑之后，做一些日志记录操作等等");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("-----End of afterAdvice()------");
		System.out.println("********************************************************************");
	}

	public void doAfterReturning(JoinPoint joinPoint){
		System.out.println("-----afterReturningAdvice().invoke-----");
		System.out.println(" 此处可以对返回值做进一步处理");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("-----End of afterReturningAdvice()------");
		System.out.println("********************************************************************");
	}

	public void doAfterThrowing(JoinPoint joinPoint, Exception ex){
		System.out.println("-----afterThrowingAdvice().invoke-----");
		System.out.println(" 错误信息：" + ex.getMessage());
		System.out.println(" 此处意在执行核心业务逻辑出错时，捕获异常，并可做一些日志记录操作等等");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("-----End of afterThrowingAdvice()------");
		System.out.println("********************************************************************");
	}
}
