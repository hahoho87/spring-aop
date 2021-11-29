package com.hahoho87.springaop.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
class AopTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfoTest() {
        assertThat(AopUtils.isAopProxy(orderService)).isFalse();
        assertThat(AopUtils.isAopProxy(orderRepository)).isFalse();
    }

    @Test
    void success() {
        orderRepository.save("itemA");
    }

    @Test
    void exceptionTest() {
        assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }


}