package io.github.sruby.springboot.demo.aware;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @date 2020/10/23 14:38
 */
@SpringBootTest
@Slf4j
class StaticContextHolderTest {

    @Test
    void getBean() {
        StaticContextHolder staticContextHolder = (StaticContextHolder) StaticContextHolder.getBean("staticContextHolder");
        log.info("staticContextHolder:{}",staticContextHolder);
    }
}