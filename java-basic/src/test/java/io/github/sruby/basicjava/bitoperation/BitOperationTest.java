package io.github.sruby.basicjava.bitoperation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @date 2020/8/12 16:00
 */
@Slf4j
public class BitOperationTest {
    @Test
    public void test() {
        int i = 10 >> 1;
        log.debug(""+i);
        int i1 = 10 << 1;
        log.debug(""+i1);
    }
}
