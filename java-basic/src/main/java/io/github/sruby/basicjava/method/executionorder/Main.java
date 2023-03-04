package io.github.sruby.basicjava.method.executionorder;

public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        c.nonStaticMethod();
        Child.staticMethod();
    }
}
