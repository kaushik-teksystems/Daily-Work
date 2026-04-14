package com.example.demo;

import com.example.demo.controller.NoteController;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeProfilerAspect {

	TimeProfilerAspect() {
		System.out.println("++++++++++++++++");
	}

	@Around("expression(* com.example.demo.service.NoteService.getOrder())")
	public Iterable calculateTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Iterable result = (Iterable) pjp.proceed();
		long end = System.currentTimeMillis();
		System.out.println("Total Time = " + (end - start));
		return result;
	}

	@Before("execution(* com.example.demo.service.NoteService.addOrd*(com.example.demo.entity.*))")
	public void logger1() {
		System.out.println("advised====================");
	}
}
