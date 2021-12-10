package com.hahoho87.springaop.internalcall;

import com.hahoho87.springaop.internalcall.aop.CallLogExpect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(CallLogExpect.class)
@SpringBootTest
class CallServiceV3Test {

    @Autowired
    CallServiceV2 callServiceV2;

    @Test
    void external() {
        callServiceV2.external();
    }
}