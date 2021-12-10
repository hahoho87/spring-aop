package com.hahoho87.springaop.proxyvs;

import com.hahoho87.springaop.member.MemberService;
import com.hahoho87.springaop.member.MemberServiceImpl;
import com.hahoho87.springaop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.security.auth.login.LoginContext;

@Slf4j
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK 동적 프록시, DI exception 발생
@SpringBootTest(properties = "spring.aop.proxy-target-class=true") // CGLIB 동적 프록시, 성공
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void doTest() {
        log.info("memberService.getClass() = {}", memberService.getClass());
        log.info("memberServiceImpl.getClass() = {}", memberServiceImpl.getClass());
    }

}
