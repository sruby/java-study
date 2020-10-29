package io.github.sruby.springboot.demo.event.applicationcontext;

import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author sruby
 * @date 2020/10/29 11:24
 */
class MethodExecutionPublisherTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        var ctx = new AnnotationConfigApplicationContext();
        ctx.scan("com.zetcode");
        ctx.refresh();


    }

}