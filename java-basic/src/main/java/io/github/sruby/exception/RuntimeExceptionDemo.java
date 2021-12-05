package io.github.sruby.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @description: test
 * @author: sruby
 * @create: 2021-11-30 09:33
 */
@Slf4j
public class RuntimeExceptionDemo {
    @Test
    public void test(){
        throw new RuntimeException();
//        log.info("1");
    }
}
