package io.github.sruby.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @date 2020/8/4 17:13
 */
public class CollecitonsTest {
    @Test
    public void test() {
        List<Integer> integers = Arrays.stream(new Integer[]{282, 389182, 383, 28}).collect(Collectors.toList());
        Collections.sort(integers);
        System.out.println(integers);
    }
}
