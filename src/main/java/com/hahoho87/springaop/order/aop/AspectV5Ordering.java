package com.hahoho87.springaop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5Ordering {

    @Aspect
    @Order(2)
    public static class LogAspect {
        @Around("com.hahoho87.springaop.order.aop.Pointcuts.allOrder())")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class transactionAspect {
        @Around("com.hahoho87.springaop.order.aop.Pointcuts.orderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[Transaction Start] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[Transaction Commit] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                log.info("[Transaction Rollback] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[Resource Release] {}", joinPoint.getSignature());
            }
        }
    }
}
