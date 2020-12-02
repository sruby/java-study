package io.github.sruby.interfacetest.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author sruby on 2020-11-27 23:09
 */
@Slf4j
public class LambdaTest {
    @Test
    public void testForEach(){
        List<String> stringList = Arrays.stream(new String[]{"1", "2", "5"}).collect(Collectors.toList());

        stringList.forEach(str -> System.out.println(str));

        stringList.forEach(System.out::println);

        //Use Java's Consumer interface to store a lambda expression in a variable
        Consumer<String> stringConsumer = System.out::println;
        stringList.forEach(stringConsumer);
    }

    @Test
    public void testMerge(){
        Map<String,Integer> map = new HashMap<>();
        map.put("a",11);
        map.put("b",12);

        Integer merge = map.merge("a", 1, (count, incr) -> count + incr);
        Integer merge2 = map.merge("a", 1, Integer::sum);

        log.info("merge:{},merge2:{},map:{}",merge,merge2,map);

    }


}
