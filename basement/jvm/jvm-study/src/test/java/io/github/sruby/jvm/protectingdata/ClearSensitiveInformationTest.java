package io.github.sruby.jvm.protectingdata;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author Sruby
 * @date 2022-3-18 15:01
 */
class ClearSensitiveInformationTest {

    @SneakyThrows
    @Test
    public void test() {
        char[] password = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        TimeUnit.MINUTES.sleep(100);
    }

}