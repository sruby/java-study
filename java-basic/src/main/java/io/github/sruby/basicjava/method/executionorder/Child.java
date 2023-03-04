package io.github.sruby.basicjava.method.executionorder;

public class Child extends Parent {
    static {
        System.out.println("Child static block");
    }

    {
        System.out.println("Child non-static block");
    }

    public Child() {
        System.out.println("Child constructor");
    }

    public static void staticMethod() {
        System.out.println("Child static method");
    }

    public void nonStaticMethod() {
        System.out.println("Child non-static method");
    }
}
