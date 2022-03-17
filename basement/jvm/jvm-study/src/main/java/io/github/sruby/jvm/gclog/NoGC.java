package io.github.sruby.jvm.gclog;

/**
 -Xms8m
 -Xmx8m
 -verbose:gc
 -XX:+PrintGCDetails
 -XX:+PrintGCDateStamps
 -XX:+PrintGCApplicationStoppedTime
 -XX:+PrintGCApplicationConcurrentTime
 -XX:+PrintHeapAtGC
 -XX:+PrintTenuringDistribution
 */
/**
 * no gc demo
 * @author sruby
 * @date 2022-3-17 22:43
 */
public class NoGC {
    public static void main(String[] args) {
        System.out.println("No gc");
    }
}