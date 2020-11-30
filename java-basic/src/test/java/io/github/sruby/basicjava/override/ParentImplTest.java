package io.github.sruby.basicjava.override;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
* ParentImpl Tester.
*
* @author <Authors name>
* @since <pre>Nov 19, 2020</pre>
* @version 1.0
*/
public class ParentImplTest {
    Parent parent = new ParentImpl();
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

        /**
    *
    * Method: a()
    *
    */
    @Test
    public void a() {
        Object a = parent.a();
        System.out.println(a);
    }
}