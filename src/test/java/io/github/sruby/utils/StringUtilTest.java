package io.github.sruby.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @date 2020/7/31 15:29
 */
public class StringUtilTest {

    @Test
    public void getUUID() {
        String uuid = StringUtil.getUUID();
        System.out.println(uuid);
    }
}