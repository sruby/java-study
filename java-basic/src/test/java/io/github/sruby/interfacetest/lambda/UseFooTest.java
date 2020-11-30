package io.github.sruby.interfacetest.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

/**
 * @author sruby on 2020-11-27 23:33
 */
@Slf4j
class UseFooTest {
    UseFoo useFoo = new UseFoo();

    @Test
    void testAdd() {
        Function<String,String> fn =  paramter -> paramter + "from lambda";
        String result = useFoo.add("test ", fn);
        log.info(result);
        Assertions.assertEquals("test from lambda", result);
    }

    @Test
    public void test(){

    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme