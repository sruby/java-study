package io.sruby.github.test.unit.parameter;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

/**
 * 从cvs转换数据为实体类,不能使用非静态内部类
 * Note that an implementation of ArgumentsAggregator
 * must be declared as either a top-level class or as a static nested class.
 *
 */
public class StateInfoAggregator implements ArgumentsAggregator {
    @Override
    public StateInfo aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
        return new StateInfo(arguments.getString(0),
                arguments.getInteger(1),
                arguments.getInteger(1));
    }
}