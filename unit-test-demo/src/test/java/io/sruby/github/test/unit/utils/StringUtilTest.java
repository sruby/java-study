package io.sruby.github.test.unit.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-07 11:35
 */
public class StringUtilTest {

    @Test
    public void testIsEmpty_OK(){
        Assertions.assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void testIsEmpty_Failed(){
        Assertions.assertFalse(StringUtils.isEmpty("not empty"));
    }

    @Test
    public void test(){

    }

}
