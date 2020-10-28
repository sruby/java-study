package io.github.sruby.springboot.demo.applicationcontext.event;

/**
 * @author sruby on 2020-10-28 21:49
 */
public enum MethodExecuteStatus {
    START("start"),END("end");
    private String status;

    MethodExecuteStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
