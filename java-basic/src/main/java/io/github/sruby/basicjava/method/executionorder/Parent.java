package io.github.sruby.basicjava.method.executionorder;

public class Parent {
    static {
        System.out.println("Parent static block");
    }
    {
        System.out.println("Parent non-static block");
    }
    public Parent() {
        System.out.println("Parent constructor");
    }
    public static void staticMethod() {
        System.out.println("Parent static method");
    }
    public void nonStaticMethod() {
        System.out.println("Parent non-static method");
    }
}

