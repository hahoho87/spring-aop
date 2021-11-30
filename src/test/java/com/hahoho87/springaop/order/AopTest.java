package com.hahoho87.springaop.order;

import com.hahoho87.springaop.order.aop.AspectV5Ordering;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
@Import({AspectV5Ordering.LogAspect.class, AspectV5Ordering.transactionAspect.class})
class AopTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfoTest() {
        log.info("isAopProxy, orderService = {}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository = {}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemId");
    }

    @Test
    void exceptionTest() {
        assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }


}