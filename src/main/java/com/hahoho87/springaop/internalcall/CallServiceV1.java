package com.hahoho87.springaop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CallServiceV1 {

    private CallServiceV1 callService;

    @Autowired
    public void setCallService(CallServiceV1 callService) {
        log.info("callServiceV1 setter={}", callService.getClass());
        this.callService = callService;
    }

    public void external() {
        log.info("call external");
        callService.internal(); // 내부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
