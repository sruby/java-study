package io.github.sruby.springboot.demo.event.applicationcontext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sruby on 2020-10-28 21:25
 */
@Slf4j
@Component
public class MethodExecutionPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;
    private List<MethodExecutionListener> list = new ArrayList<>();

    public  void methodToMonitor(){
        MethodExecutionEvent methodExecutionEvent = new MethodExecutionEvent(this,"methodToMonitor",MethodExecuteStatus.START);
        applicationEventPublisher.publishEvent(methodExecutionEvent);
        log.info("methodToMonitor executing");
        MethodExecutionEvent methodExecutionEvent1 = new MethodExecutionEvent(this,"methodToMonitor",MethodExecuteStatus.END);
        applicationEventPublisher.publishEvent(methodExecutionEvent1);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
