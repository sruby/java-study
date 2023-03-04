package io.github.sruby.designpattern.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * TODO
 *
 * @author Sruby
 * @date 2023-2-26 19:08
 */
class SingletonWithStaticTest {
    @Test
    public void getSingle() {
        SingletonWithStatic single = SingletonWithStatic.getSingle();
        SingletonWithStatic single2 = SingletonWithStatic.getSingle();
        assertSame(single, single2);
    }

    @Test
    public void testgetSingleInMultiThreadedEnvironment() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            SingletonWithStatic instance1 = SingletonWithStatic.getSingle();
            assertNotNull(instance1);
        });

        Thread thread2 = new Thread(() -> {
            SingletonWithStatic instance2 = SingletonWithStatic.getSingle();
            assertNotNull(instance2);
        });

        thread1.start();
        Thread.sleep(100);
        thread2.start();

        thread1.join();
        thread2.join();
        SingletonWithStatic instance1 = SingletonWithStatic.getSingle();
        SingletonWithStatic instance2 = SingletonWithStatic.getSingle();
        assertEquals(instance1, instance2);
    }
}