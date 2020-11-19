package io.github.sruby.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-11-19 10:29
 */
@Slf4j
public class LogTest {
    @Test
    public void test(){
      log.info("log:{}",Arrays.asList(new Long[]{10L,20L}));
    }
}
