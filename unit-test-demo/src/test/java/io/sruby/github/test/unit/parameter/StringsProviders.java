package io.sruby.github.test.unit.parameter;

import java.util.stream.Stream;

public class StringsProviders {
    public static Stream<String> tinyStrings() {
        return Stream.of(".", "oo", "OOO");
    }
}