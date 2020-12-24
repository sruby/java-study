package io.sruby.github.test.unit.parameter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-24 10:05
 */
public class CustomAggregatorTest {
    @ParameterizedTest
    @CsvSource({
            "FL, 1, 1",
            "OH, 2, 2",
            "MI, 3, 3"
    })
    void csvInputTest(@CsvToStateInfo StateInfo stateInfo) {
        System.out.println(stateInfo.getStateCode() + " - " + stateInfo.getVal1() + " - " + stateInfo.getVal2());
    }
}
