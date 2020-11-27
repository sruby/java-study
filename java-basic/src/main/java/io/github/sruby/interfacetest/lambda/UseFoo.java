package io.github.sruby.interfacetest.lambda;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author sruby on 2020-11-27 23:32
 */
public class UseFoo {
    public String add(String str, Function<String, String> fn){
        return fn.apply(str);
    }

    public void print(String str, Consumer fn){
        fn.accept(str);
    }
}
