package io.github.sruby.springboot.demo.event.java;

import java.util.EventObject;

/**
 * event
 * @author sruby on 2020-10-28 21:18
 */
public class MethodExecutionEvent extends EventObject {
    private String method;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MethodExecutionEvent(Object source) {
        super(source);
    }

    public MethodExecutionEvent(Object source, String method) {
        super(source);
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
