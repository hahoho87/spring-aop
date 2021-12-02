package com.hahoho87.springaop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

//    @Around("com.hahoho87.springaop.order.aop.Pointcuts.orderAndService()")
//    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            // @Before
//            log.info("[Transaction Start] {}", joinPoint.getSignature());
//            Object result = joinPoint.proceed();
//            // @AfterReturning
//            log.info("[Transaction Commit] {}", joinPoint.getSignature());
//            return result;
//        } catch (Exception e) {
//            // @AfterThrowing
//            log.info("[Transaction Rollback] {}", joinPoint.getSignature());
//            throw e;
//        } finally {
//            log.info("[Resource Release] {}", joinPoint.getSignature());
//        }
//    }

    @Before("com.hahoho87.springaop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "com.hahoho87.springaop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {} return = {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "com.hahoho87.springaop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrow(JoinPoint joinPoint, Exception ex) {
        log.info("[exception] {} message = {}", joinPoint.getSignature(), ex);
    }

    @After("com.hahoho87.springaop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }


}
