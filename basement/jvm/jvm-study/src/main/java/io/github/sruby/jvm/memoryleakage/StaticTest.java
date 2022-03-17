package io.github.sruby.jvm.memoryleakage;

import java.util.ArrayList;
import java.util.List;

/**
 *  Memory Leak Through static Fields
 * @author sruby
 * @date 2022-3-17 22:46
 * @return
 */
public class StaticTest {
    public static List<Double> list = new ArrayList<>();

    public void populateList() {
        for (int i = 0; i < 10000000; i++) {
            list.add(Math.random());
        }
        System.out.println("Debug Point 2");
    }

    public static void main(String[] args) {
        System.out.println("Debug Point 1");
        new StaticTest().populateList();
        System.out.println("Debug Point 3");
    }
}