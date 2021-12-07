package com.hahoho87.springaop.member;

import com.hahoho87.springaop.member.annotation.ClassAop;
import com.hahoho87.springaop.member.annotation.MethodAop;
import org.springframework.stereotype.Service;

@Service
@ClassAop
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
