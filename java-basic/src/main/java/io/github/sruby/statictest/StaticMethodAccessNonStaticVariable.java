package io.github.sruby.statictest;

/**
 * @description:
 * @author: sruby
 * @create: 2021-03-25 18:16
 */
public class StaticMethodAccessNonStaticVariable {
    int x;
    static StaticMethodAccessNonStaticVariable staticMethodAccessNonStaticVariable = new StaticMethodAccessNonStaticVariable();

    public static void doSthStatically() {
//        x = 0; //doesn't work!
        staticMethodAccessNonStaticVariable.x = 0;
    }
}
