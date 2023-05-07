package io.github.sruby.basicjava.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@Slf4j
public class StreamTest {
    /**
     * list转成id为key，person为value的结构
     */
    @Test
    public void testToMap() {
        List<Person> persons = Arrays.asList(
                new Person(1, "Alice"),
                new Person(2, "Bob"),
                new Person(3, "Charlie"),
                new Person(4, "David")
        );
        Map<Integer, Person> idToPerson = persons.stream()
                .collect(Collectors.toMap(Person::getId, Function.identity()));
        idToPerson.forEach((key, value) -> System.out.println(key + " -> " + value));

//        Map<Integer, Person> idToPerson = persons.stream()
//                .collect(Collectors.toMap(Person::getId, Function.identity(), (p1, p2) -> p1.merge(p2)));

    }

    /**
     * list转成id为key，name为value的结构
     */
    @Test
    public void testToMap2() {
        List<Person> persons = Arrays.asList(
                new Person(1, "Alice"),
                new Person(2, "Bob"),
                new Person(3, "Charlie"),
                new Person(4, "David")
        );
        Map<Integer, String> idToPerson = persons.stream()
                .collect(Collectors.toMap(Person::getId,Person::getName));
        idToPerson.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
    @Test()
    public void testToMap_illegalStateException_duplicationKey() {
        List<Person> persons = Arrays.asList(
                new Person(1, "Alice"),
                new Person(1, "Bob"),
                new Person(3, "Charlie"),
                new Person(4, "David")
        );

        Assertions.assertThrows(IllegalStateException.class, () -> {
            Map<Integer, Person> idToPerson = persons.stream()
                    .collect(Collectors.toMap(Person::getId, Function.identity()));
            idToPerson.forEach((key, value) -> System.out.println(key + " -> " + value));
        });
//        Map<Integer, Person> idToPerson = persons.stream()
//                .collect(Collectors.toMap(Person::getId, Function.identity(), (p1, p2) -> p1.merge(p2)));

    }

    @Test
    public void testFilter() {
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis","Hollis666", "Hello", "HelloWorld", "Hollis");
        strings  = strings.stream().filter(string -> string.startsWith("Hollis")).collect(Collectors.toList());
        System.out.println(strings);
        //Hollis, HollisChuang, Hollis666, Hollis
    }

    /**
     *  延迟执行
     * count 执行
     * Predicate.test 执行
     * Predicate.test 执行
     * Predicate.test 执行
     * Predicate.test 执行
     */
    @Test
    public void laziness(){
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        Stream<Integer> stream = strings.stream().filter(new Predicate() {
            @Override
            public boolean test(Object o) {
                System.out.println("Predicate.test 执行");
                return true;
            }
        });

        System.out.println("count 执行");
        stream.count();
    }

    /**
     *  Infinite Streams
     */
    @Test
    public void testLimit() {
        // given
        Stream<Integer> infiniteStream = Stream.iterate(0, i -> i + 2);
        // when
        List<Integer> collect = infiniteStream
                .limit(10)
                .collect(Collectors.toList());
        log.info("collect:{}",collect);
        // then
        assertEquals(collect, Arrays.asList(0, 2, 4, 6, 8, 10, 12, 14, 16, 18));
    }

    /**
     *  Infinite Stream of a Custom Type of Elements
     */
    @Test
    public void testInfiniteStreamOfCustomType() {
        Supplier<UUID> randomUUIDSupplier = UUID::randomUUID;
        Stream<UUID> infiniteStreamOfRandomUUID = Stream.generate(randomUUIDSupplier);
        List<UUID> randomInts = infiniteStreamOfRandomUUID
                .skip(10)
                .limit(10)
                .collect(Collectors.toList());
        log.info("randomInts:{}",randomInts);
    }

    @Test
    public void testDoWhile_theStreamWay() {
        Stream<Integer> integers = Stream
                .iterate(0, i -> i + 1);
        integers
                .limit(10)
                .forEach(System.out::println);
    }


}
