package io.github.sruby.guava;

import com.google.common.collect.Collections2;
import io.github.sruby.annotation.demo.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-11-17 11:42
 */
public class Collections2Test {

    @Test
    public void filter(){
        List<Person> list = new ArrayList<>();
        list.add(Person.builder().name("test").build());
        list.add(Person.builder().name("test").build());
        list.add(Person.builder().name("test2").build());

        Collection<Person> filter = Collections2.filter(list, (e) -> e.getName().equals("test"));
    }

    @Test
    public void test(){
        List<Person> list = new ArrayList<>();
        list.add(Person.builder().name("test").build());
        list.add(Person.builder().name("test").build());
        list.add(Person.builder().name("test2").build());
    }
}
