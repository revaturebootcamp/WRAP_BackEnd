package com.revature.aspect;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.revature.models.UserAccount;

@Component
@Aspect
public class LoggingAspect {
	private static Logger logger = Logger.getLogger(LoggingAspect.class);
	
//	@AfterReturning (
//			pointcut="execution(* *(..))",
//			returning="ret")
//	public void login (/*JoinPoint jp,*/ Cookie ret) {
//		UserAccount user = (UserAccount) jp.getArgs()[0];
//		
//		if (null == user) {
//			logger.warn ("Failed login by improper account: " + jp.getArgs()[0]);
//			return;
//		}
//		
//		if (null == ret) {
//			logger.warn("Failed login, invalid credentials for username: " + user.getUsername());
//		}
//		else {
//			logger.info("Succesful login for username: " + user.getUsername());
//		}
//	}
	
//	@After("execution(* *(..))")
//	public void logAfter(JoinPoint jp) {
//		logger.info("The " + jp.getTarget().getClass().getName()+"."+jp.getSignature().getName()
//				+ " AFTER");
//	}
	
//	@Before(value = "")
//	private void anyPublicOperation() {}

}
