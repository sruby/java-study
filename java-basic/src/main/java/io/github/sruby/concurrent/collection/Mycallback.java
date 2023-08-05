package io.github.sruby.concurrent.collection;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * call back sample
 *
 * @author Sruby
 * @date 2/8/2023 15:13
 */
public class Mycallback implements Callable {

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("callback");
        return "result";
    }
}
