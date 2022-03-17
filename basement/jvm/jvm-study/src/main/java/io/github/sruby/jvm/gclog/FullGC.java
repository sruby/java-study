package io.github.sruby.jvm.gclog;

/**
 * full gc demo 
 * @author sruby 
 * @date 2022-3-17 22:42 
 */
public class FullGC {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] b = new byte[size];

        b = null;

        System.gc();
    }
}