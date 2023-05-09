package io.github.sruby.basicjava.classname;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ClassNameTest {
    @Test
    public void test() {
        String simpleName = new ClassNameTest().getClass().getSimpleName();
        boolean classNameTest = simpleName.equals("ClassNameTest");
        log.info("simpleName:{},result:{}",simpleName,classNameTest);
    }
}
