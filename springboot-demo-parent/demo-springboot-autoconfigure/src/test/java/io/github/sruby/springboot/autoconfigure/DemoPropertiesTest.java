package io.github.sruby.springboot.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


/**
 * @date 2020/10/19 17:08
 */
@Slf4j
class DemoPropertiesTest {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();


    @Test
    public void test() throws IOException {
//        load(DemoProperties.class, "demo.name=foo");
    }

}