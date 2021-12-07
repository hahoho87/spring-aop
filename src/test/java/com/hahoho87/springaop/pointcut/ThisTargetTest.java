package com.hahoho87.springaop.pointcut;

import com.hahoho87.springaop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK 동적 프록시
@SpringBootTest(properties = "spring.aop.proxy-target-class=true") // CGLib proxy
@Import(ThisTargetTest.ThisTargetAspect.class)
public class ThisTargetTest {

    @Autowired
    MemberService memberService;

    @Test
    void successTest() {
        log.info("memberService proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ThisTargetAspect {
        @Around("this(com.hahoho87.springaop.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(com.hahoho87.springaop.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("this(com.hahoho87.springaop.member.MemberServiceImpl)")
        public Object doThisImpl(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(com.hahoho87.springaop.member.MemberServiceImpl)")
        public Object doTargetImpl(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-Impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
