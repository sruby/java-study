package io.github.sruby.springboot.demo.aware;

import io.github.sruby.springboot.autoconfigure.StaticContextHolderInAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @date 2020/10/23 14:38
 */
@SpringBootTest
@Slf4j
class StaticContextHolderInAutoConfigureTest {

    @Test
    void getBean() {
        StaticContextHolderInAutoConfigure staticContextHolderInAutoConfigure = (StaticContextHolderInAutoConfigure) StaticContextHolderInAutoConfigure.getBean("staticContextHolderInAutoConfigure");
        log.info("staticContextHolderInAutoConfigure:{}",staticContextHolderInAutoConfigure);
    }
}