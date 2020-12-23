package io.sruby.github.test.unit.parameter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.platform.commons.util.StringUtils;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-23 17:21
 */
public class ParameterTest {
    @ParameterizedTest
    @ValueSource(strings = {"a","b","c"})
    public void test(String param){
        assertTrue(StringUtils.isNotBlank(param));
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void testWithValueSource(int argument) {
        assertThat(argument).isGreaterThan(0).isLessThan(4);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n" })
    void nullEmptyAndBlankStrings(String text) {
        assertThat(text == null || text.trim().isEmpty()).isTrue();
    }

    @ParameterizedTest
    @EnumSource
    void testWithEnumSourceWithAutoDetection(ChronoUnit unit) {
        System.out.println(unit);
    }

    @ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void testWithEnumSource(TemporalUnit unit) {
        System.out.println(unit);
    }

    @ParameterizedTest
    @EnumSource(names = { "DAYS", "HOURS" })
    void testWithEnumSourceInclude(ChronoUnit unit) {
        assertThat(unit).isIn(ChronoUnit.DAYS, ChronoUnit.HOURS);
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    public void testWithExplicitLocalMethodSource(String argument){
        assertThat(argument).isIn("a","b");
    }
    static Stream<String> stringProvider(){
        return Stream.of("a","b");
    }


    /**
     * 如果没有在@MethodSource注解中指定工厂方法的名字，
     * JUnit Jupiter将在测试类中寻找和参数化测试方法同名的方法作为工厂方法
     * @param argument
     */
    @ParameterizedTest
    @MethodSource
    void testWithDefaultLocalMethodSource(String argument) {
        assertThat(argument).isIn("apple", "banana");
    }

    static Stream<String> testWithDefaultLocalMethodSource() {
        return Stream.of("apple", "banana");
    }

    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
        assertThat(argument).isLessThan(20).isGreaterThan(9);
    }

    static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }


    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertThat(str).hasSize(5);
        assertThat(num).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(2);
        assertThat(list).hasSize(2);
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.arguments("apple", 1, Arrays.asList("a", "b")),
                Arguments.arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }

    @ParameterizedTest
    @MethodSource("io.sruby.github.test.unit.parameter.StringsProviders#tinyStrings")
    void testWithExternalMethodSource(String tinyString) {
        assertThat(tinyString).isIn(".", "oo", "OOO");
    }
}
