package io.github.sruby.springboot.demo.applicationcontext.event;

import java.util.EventListener;

/**
 * @author sruby on 2020-10-28 21:23
 */
public interface MethodExecutionListener extends EventListener {
    void onMethodExecute(MethodExecutionEvent methodExecutionEvent);
    void onMethodEnd(MethodExecutionEvent methodExecutionEvent);
}
