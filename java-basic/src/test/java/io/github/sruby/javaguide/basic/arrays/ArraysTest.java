package io.github.sruby.javaguide.basic.arrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @date 2020/8/4 17:17
 */
public class ArraysTest {
    @Test
    public void test() {
        List<Integer> integers = Arrays.stream(new Integer[]{282, 389182, 383, 28}).collect(Collectors.toList());
        System.out.println(integers);
    }
}
