package io.github.sruby.basicjava.override;

import org.junit.Test;

/**
 * @date 2020/8/13 17:02
 */
public class ParentImplTest {
    Parent parent = new ParentImpl();

    @Test
    public void a() {
        Object a = parent.a();
        System.out.println(a);
    }
}