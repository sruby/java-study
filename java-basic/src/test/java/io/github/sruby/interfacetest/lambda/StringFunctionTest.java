package io.github.sruby.interfacetest.lambda;

import org.junit.jupiter.api.Test;

/**
 *
 * @author sruby on 2020-11-27 23:26
 */
class StringFunctionTest {

    /**
     * Create a method which takes a lambda expression as a parameter
     */
    @Test
    public void test(){
        StringFunction  exclaim = (s) -> s+ "!";
        StringFunction  ask = (s) -> s+ "?";
        printFormatted("we",exclaim);
        printFormatted("he",ask);
    }

    public void printFormatted(String str,StringFunction format){
        String run = format.run(str);
        System.out.println(run);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme