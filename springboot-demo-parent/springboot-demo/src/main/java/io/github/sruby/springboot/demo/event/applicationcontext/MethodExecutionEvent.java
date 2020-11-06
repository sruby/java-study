package io.github.sruby.springboot.demo.event.applicationcontext;

import org.springframework.context.ApplicationEvent;

/**
 * event
 * @author sruby on 2020-10-28 21:18
 */
public class MethodExecutionEvent extends ApplicationEvent {
    private String method;
    private MethodExecuteStatus methodExecuteStatus;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MethodExecutionEvent(Object source) {
        super(source);
    }

    public MethodExecutionEvent(Object source, String method, MethodExecuteStatus methodExecuteStatus) {
        super(source);
        this.method = method;
        this.methodExecuteStatus = methodExecuteStatus;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public MethodExecuteStatus getMethodExecuteStatus() {
        return methodExecuteStatus;
    }

    public void setMethodExecuteStatus(MethodExecuteStatus methodExecuteStatus) {
        this.methodExecuteStatus = methodExecuteStatus;
    }
}
