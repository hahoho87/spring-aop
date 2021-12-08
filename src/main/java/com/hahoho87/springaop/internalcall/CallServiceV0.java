package com.hahoho87.springaop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CallServiceV0 {

    public void external() {
        log.info("call external");
        this.internal(); // 내부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
