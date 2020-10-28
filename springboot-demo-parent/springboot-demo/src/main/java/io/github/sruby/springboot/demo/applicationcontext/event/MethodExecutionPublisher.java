package io.github.sruby.springboot.demo.applicationcontext.event;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import	java.util.List;

/**
 * @author sruby on 2020-10-28 21:25
 */
@Slf4j
public class MethodExecutionPublisher {
    private List<MethodExecutionListener> list = new ArrayList<>();

    public  void methodToMonitor(){
        MethodExecutionEvent methodExecutionEvent = new MethodExecutionEvent(this,"methodToMonitor");
        publishEvent(methodExecutionEvent,MethodExecuteStatus.START);
        log.info("methodToMonitor executing");
        publishEvent(methodExecutionEvent,MethodExecuteStatus.END);
    }

    public void publishEvent(MethodExecutionEvent methodExecutionEvent,MethodExecuteStatus methodExecuteStatus){
        List<MethodExecutionListener> listCopy = this.list;
        for(MethodExecutionListener methodExecutionListener:listCopy){
            if (MethodExecuteStatus.START.equals(methodExecuteStatus)){
                methodExecutionListener.onMethodExecute(methodExecutionEvent);
            }else {
                methodExecutionListener.onMethodEnd(methodExecutionEvent);
            }
        }
    }

    public void addListener(MethodExecutionListener methodExecutionListener){
        list.add(methodExecutionListener);
    }

    public void removeListener(MethodExecutionListener executionListener){
        if (list.contains(executionListener)){
            list.remove(executionListener);
        }
    }

    public static void main(String[] args) {
        MethodExecutionPublisher methodExecutionPublisher = new MethodExecutionPublisher();
        methodExecutionPublisher.addListener(new MethodExecutionListenerImpl());
        methodExecutionPublisher.methodToMonitor();
    }
}
