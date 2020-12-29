package io.sruby.github.test.unit.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-28 15:36
 */
public class BeforeAllTest {
    private static Integer num;

    @BeforeAll
    public static void beforeAll(){
        num = 1;
    }

    @Test
    public void test(){
        assertThat(num).isEqualTo(1);
    }
}
