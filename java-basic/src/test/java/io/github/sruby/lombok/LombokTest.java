package io.github.sruby.lombok;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-28 15:24
 */
public class LombokTest {
    @SneakyThrows
    @Test
    public void testSneakyThrows(){
        throw new Exception("myexception");
    }

}
