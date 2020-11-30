package io.github.sruby.interfacetest.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author sruby on 2020-11-27 23:09
 */
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


}
