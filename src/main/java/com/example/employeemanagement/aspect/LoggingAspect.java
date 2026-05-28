package com.example.employeemanagement.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Log ten method truoc khi goi
    @Before("execution(* com.example.employeemanagement.controller.*.*(..))")
    public void logBeforeController(JoinPoint joinPoint) {
        logger.info("[BEFORE] Method dang duoc goi: {}", joinPoint.getSignature().getName());
    }

    // Log ket qua tra ve tu service
    @AfterReturning(
        pointcut = "execution(* com.example.employeemanagement.service.*.*(..))",
        returning = "result"
    )
    public void logAfterReturningService(JoinPoint joinPoint, Object result) {
        if (result != null) {
            logger.info("[AFTER RETURNING] Method {} tra ve: {}", joinPoint.getSignature().getName(), result.toString());
        } else {
            logger.info("[AFTER RETURNING] Method {} tra ve: null", joinPoint.getSignature().getName());
        }
    }

    // Do thoi gian thuc thi cua controller
    @Around("execution(* com.example.employeemanagement.controller.*.*(..))")
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("[AROUND] Method {} thuc thi trong {} ms", joinPoint.getSignature().getName(), (endTime - startTime));
        return result;
    }
}
