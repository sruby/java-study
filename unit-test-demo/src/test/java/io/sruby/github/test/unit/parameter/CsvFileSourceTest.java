package io.sruby.github.test.unit.parameter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-23 17:55
 */
public class CsvFileSourceTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void testWithCsvFileSource(String country, int reference) {
        assertThat(country).isIn("Sweden", "Poland", "United States of America");
        assertThat(reference).isPositive();
    }
}
