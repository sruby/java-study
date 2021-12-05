package io.github.sruby.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @description: test
 * @author: sruby
 * @create: 2021-11-19 14:10
 */
@Slf4j
public class ArraysTest {
    @Test
    public void test(){
        int[] ints = Arrays.stream(new int[]{1, 2, 3}).filter(x -> x == 1).toArray();
    }
}
