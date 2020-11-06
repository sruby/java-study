package io.github.sruby.springboot.demo.event.applicationcontext;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author sruby
 * @date 2020/10/29 11:24
 */
class MethodExecutionPublisherTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("io.github.sruby.springboot.demo.event");
        applicationContext.refresh();

        MethodExecutionPublisher methodExecutionPublisher = (MethodExecutionPublisher) applicationContext.getBean("methodExecutionPublisher");
        methodExecutionPublisher.methodToMonitor();
    }

}