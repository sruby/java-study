package io.github.sruby.springboot.demo.applicationcontext.event;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sruby on 2020-10-28 21:38
 */
@Slf4j
public class MethodExecutionListenerImpl implements MethodExecutionListener {
    @Override
    public void onMethodExecute(MethodExecutionEvent methodExecutionEvent) {
        String method = methodExecutionEvent.getMethod();
        log.info("onMethodExecute:{}",method);
    }

    @Override
    public void onMethodEnd(MethodExecutionEvent methodExecutionEvent) {
        String method = methodExecutionEvent.getMethod();
        log.info("onMethodEnd:{}",method);
    }
}
