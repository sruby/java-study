package io.github.sruby.basicjava.util.steam.collectors;

import io.github.sruby.annotation.demo.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-11-17 14:50
 */
@Slf4j
public class SteamTest {
    @Test
    public void stream(){
        List<Person> list = new ArrayList<>();
        list.add(Person.builder().name("test").build());
        list.add(Person.builder().name("test").build());
        list.add(Person.builder().name("test2").build());

        List<String> stringList = list.stream().map(person -> person.getName())
                .collect(Collectors.toList());
        log.info("result:{}",stringList);
    }
}
