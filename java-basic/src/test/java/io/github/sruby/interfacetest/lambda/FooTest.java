package io.github.sruby.interfacetest.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author sruby on 2020-11-27 23:46
 */
@Slf4j
class FooTest {
    @Test
    public void test(){
        Foo fo = str -> str + "from foo";

        //over an inner class
        Foo fooByIC = new Foo() {
            @Override
            public String method(String string) {
                return string + " from Foo";
            }
        };

        String lambda_str_ = fo.method("lambda str ");
        String inter_class_ = fooByIC.method("inter class ");
        log.info(lambda_str_);
        log.info(inter_class_);
    }

}