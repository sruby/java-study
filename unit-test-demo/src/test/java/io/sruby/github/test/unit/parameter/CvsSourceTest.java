package io.sruby.github.test.unit.parameter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-23 17:51
 */
public class CvsSourceTest {
    @ParameterizedTest
    @CsvSource({
            "apple,         1",
            "banana,        2",
            "'lemon, lime', 0xF1"
    })
    void testWithCsvSource(String fruit, int rank) {
        assertThat(fruit).isIn("apple", "banana", "lemon, lime");
        assertThat(rank).isNotEqualTo(0);
    }
    @ParameterizedTest
    @CsvSource({
            "apple,1",
            "banana,2",
            "'lemon, lime', 0xF1"
    })
    void testWithCsvSource_List(String fruit, List<Integer> rank) {
        assertThat(fruit).isIn("apple", "banana", "lemon, lime");
        assertThat(rank).isNotEqualTo(0);
    }



}
