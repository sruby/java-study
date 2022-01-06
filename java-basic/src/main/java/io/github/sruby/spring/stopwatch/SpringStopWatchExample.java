package io.github.sruby.spring.stopwatch;


import org.junit.Test;
import org.springframework.util.StopWatch;

public class SpringStopWatchExample {
 
    public static void main (String[] args) throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start();
        //long task simulation
        Thread.sleep(1000);
        sw.stop();
        System.out.println(sw.getTotalTimeMillis());
    }

    @Test
    public void test1() throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start("A");//setting a task name
        //long task simulation
        Thread.sleep(1000);
        sw.stop();
        System.out.println(sw.getLastTaskTimeMillis());
    }

    @Test
    public void test2() throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start("A");
        Thread.sleep(500);
        sw.stop();
        sw.start("B");
        Thread.sleep(300);
        sw.stop();
        sw.start("C");
        Thread.sleep(200);
        sw.stop();
        System.out.println(sw.prettyPrint());
    }
}
