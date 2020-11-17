package io.github.sruby.basicjava.util.steam.collectors;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

/**
 * @description: test
 * https://www.baeldung.com/java-8-collectors
 * @author: sruby
 * @create: 2020-11-17 13:53
 */
@Slf4j
public class CollectorsTest {

    private List<String> givenList;

    @Before
    public void before(){
        givenList = Arrays.asList("a", "bb", "ccc", "dd");
    }
    @Test
    public void testToList(){
        log.info("givenList:{}", givenList);
        List<String> result = givenList.stream()
                .collect(toList());
        log.info("givenList:{}", givenList);

    }

    @Test
    public void testToUnmodifiableList(){
        List<String> result = givenList.stream()
                .collect(toUnmodifiableList());
//        assertThatThrownBy(() -> result.add("foo"))
//                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testtoSet(){
        Set<String> resultSet = givenList.stream()
                .collect(toSet());
    }

    @Test
    public void testtoMap(){
        Map<String, Integer> result = givenList.stream()
                .collect(toMap(Function.identity(), String::length));
        log.info("result:{}",result);

    }

    @Test
    public void testcollectingAndThen(){
        List<String> result = givenList.stream()
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));


    }

    @Test
    public void testjoining(){
        String result = givenList.stream()
                .collect(joining());
    }
}
