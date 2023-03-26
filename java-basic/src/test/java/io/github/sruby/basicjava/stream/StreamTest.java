package io.github.sruby.basicjava.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamTest {
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
}
