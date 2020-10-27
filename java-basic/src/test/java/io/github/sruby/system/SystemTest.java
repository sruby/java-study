package io.github.sruby.system;

import org.junit.Test;

import java.util.Properties;

/**
 * @date 2020/9/11 14:22
 */
public class SystemTest {
    @Test
    public void test() {
        Properties properties = System.getProperties();
        System.out.println(properties);
    }
}
