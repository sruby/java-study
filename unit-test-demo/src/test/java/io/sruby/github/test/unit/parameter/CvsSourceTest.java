package io.sruby.github.test.unit.parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.CsvSource;

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


    /**
     * 从cvs转换数据为复杂实体类
     */
    public class StateInfoAggregator implements ArgumentsAggregator {
        @Override
        public StateInfo aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
            return new StateInfo(arguments.getString(0),
                    arguments.getInteger(1),
                    arguments.getInteger(1));

        }
    }

    @ParameterizedTest
    @CsvSource({
            "FL, 1, 1",
            "OH, 2, 2",
            "MI, 3, 3"
    })
    void csvInputTest(@AggregateWith(StateInfoAggregator.class) StateInfo stateInfo) {
        System.out.println(stateInfo.getStateCode() + " - " + stateInfo.getVal1() + " - " + stateInfo.getVal2());
    }
    @Data
    @AllArgsConstructor
    class StateInfo{
        private String stateCode;
        private Integer val1;
        private Integer val2;
    }
}
