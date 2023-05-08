package io.github.sruby.spring;

import io.github.sruby.annotation.demo.Person;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * test
 *
 * @date 2023/5/5 14:03
 */
public class BeanUtilsTest {
    /**
     * 如果拷贝2个不同对象的属性到一个对象，2个对象的属性没有重复
     */
    @Test
    public void test() {
        BeanUtils.copyProperties(Person.builder().name("阿特罗伯斯").build(), Person.builder().build());
    }
}
