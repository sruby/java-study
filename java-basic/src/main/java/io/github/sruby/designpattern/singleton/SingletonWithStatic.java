package io.github.sruby.designpattern.singleton;

/**
 * Singleton with static
 *
 * @author Sruby
 * @date 2023-2-26 19:06
 */
public class SingletonWithStatic {
    private SingletonWithStatic sigletonWithStatic(){
        return new SingletonWithStatic();
    }
    private static SingletonWithStatic sigletonWithStatic;
    static {
        sigletonWithStatic = new SingletonWithStatic();
    }

    public static SingletonWithStatic getSingle(){
        return sigletonWithStatic;
    }
}
