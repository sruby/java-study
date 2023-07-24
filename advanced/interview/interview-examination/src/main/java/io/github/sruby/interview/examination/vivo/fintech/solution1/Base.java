package io.github.sruby.interview.examination.vivo.fintech.solution1;

/**
 * 外部接口调用基类
 *
 * @author Sruby
 * @date 7/22/2023 10:27 PM
 */
public abstract class Base extends Thread{
    public void before(){
        System.out.println("模拟前置处理");
    }

    public String execute(){
        before();

        String result = invoke();

        after();

        return result;
    }

    public void run(){
        execute();
    }


    /**
     * 外部接口调用
     *
     * @return
     */
    public abstract String invoke();

    public void after(){
        System.out.println("模拟后置处理");
    }
}
