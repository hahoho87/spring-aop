package com.hahoho87.springaop.member;

public class MemberServiceImpl implements MemberService {

    @Override
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
