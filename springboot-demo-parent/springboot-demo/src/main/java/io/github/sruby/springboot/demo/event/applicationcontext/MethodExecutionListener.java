package io.github.sruby.springboot.demo.event.applicationcontext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author sruby on 2020-10-28 21:38
 */
@Slf4j
@Component
public class MethodExecutionListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof MethodExecutionEvent){
            MethodExecuteStatus methodExecuteStatus = ((MethodExecutionEvent) event).getMethodExecuteStatus();
            if (methodExecuteStatus.equals(MethodExecuteStatus.END)){
                log.info("onApplicationEvent end");
            }else {
                log.info("onApplicationEvent start");
            }
        }
    }
}
