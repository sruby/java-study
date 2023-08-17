package io.github.sruby.concurrent;

public class SleepExample {
    public static void main(String[] args) {
        System.out.println("Start of the program");
        
        // Sleep for 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("End of the program");
    }
}
