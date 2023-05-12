package io.github.sruby.basicjava.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * stream merge test
 *
 * @date 5/12/2023 7:49 PM
 */
public class StreamMergeTest {
    @Test
    public void test() {
        List<Person> list1 = new ArrayList<>();
        list1.add(new Person("张三", 20));
        list1.add(new Person("李四", 25));

        List<Person> list2 = new ArrayList<>();
        list2.add(new Person("王五", 30));
        list2.add(new Person("张三", 35));

        Map<String, List<Person>> map1 = list1.stream().collect(Collectors.groupingBy(Person::getName));
        Map<String, List<Person>> map2 = list2.stream().collect(Collectors.groupingBy(Person::getName));

        Map<String, List<Person>> result = new HashMap<>();

        map1.forEach((key, value) -> result.merge(key, value, (oldValue, newValue) -> {
            oldValue.addAll(newValue);
            return oldValue;
        }));

        map2.forEach((key, value) -> result.merge(key, value, (oldValue, newValue) -> {
            oldValue.addAll(newValue);
            return oldValue;
        }));

        result.forEach((key, value) -> {
            if (value.size() < 2) {
                value.add(new Person(key, -1));
            }
        });

        List<Person> listResult = result.values().stream()
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        listResult.forEach(person -> System.out.println(person.getName() + " : " + person.getAge()));
    }

    public class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }


}
