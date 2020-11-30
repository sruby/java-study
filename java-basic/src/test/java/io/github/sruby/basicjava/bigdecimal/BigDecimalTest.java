package io.github.sruby.basicjava.bigdecimal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @description: tst
 * @author: sruby
 * @create: 2020-11-26 14:02
 */
@Slf4j
public class BigDecimalTest {
    @Test
    public void test(){
        BigDecimal bigDecimal = new BigDecimal("8888888888888888888888888888888888888888.8888888888888888888888888888888888888888");
        log.info(bigDecimal.toString());
    }
}
