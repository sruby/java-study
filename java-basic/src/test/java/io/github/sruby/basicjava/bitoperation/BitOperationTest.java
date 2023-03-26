package io.github.sruby.basicjava.bitoperation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

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

    @Test
    public void testBitnumofString() {
        String s = "Hello, world!";
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        int bits = bytes.length * 8;
        System.out.println("Number of bits: " + bits);
        System.out.println("Number of String: " + s.length());
        System.out.println("bit number of per string: " + bits/s.length());
    }

    @Test
    public void testShift() {
        log.info(String.valueOf(SnowFlakeWorker.MAX_SEQUENCE));
    }
}
