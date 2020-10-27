package io.github.sruby.springboot.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @date 2020/10/19 17:08
 */
@SpringBootTest
@Slf4j
class DemoPropertiesTest {
    @Value("${demo.name}")
    private String name;
    @Value("${demo.age}")
    private String age;


    @Test
    public void test() {
        log.info("name:{}",name);
    }
    @Test
    public void getParentProperty() {
        log.info("age:{}",age);
    }

}

