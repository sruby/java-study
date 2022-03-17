package io.github.sruby.jvm.gclog;

/**
 * normal gc demo
 * @author sruby
 * @date 2022-3-17 22:44
 */
public class NormalGC {
    public static void main(String[] args) throws InterruptedException {

        Object o  = new Object();
        for (int i = 0; ;i++) {
            Thread.sleep(1000);
            byte[] b = new byte[1024 * 1024];
            b = null;
        }
    }
}