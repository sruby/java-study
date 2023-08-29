package io.github.sruby.concurrent.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 使用 PipedOutputStream 和 PipedInputStream 创建了一个字节流的管道。
 * senderThread 线程向管道中写入字节流数据，
 * 而 receiverThread 线程从管道中读取字节流数据。通过管道的连接，实现了线程间的数据交换。
 */
public class BytePipeExample {
    public static void main(String[] args) {
        try {
            final PipedOutputStream output = new PipedOutputStream();
            final PipedInputStream input = new PipedInputStream(output);

            Thread senderThread = new Thread(() -> {
                try {
                    String message = "Hello, World!";
                    output.write(message.getBytes());
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread receiverThread = new Thread(() -> {
                try {
                    byte[] buffer = new byte[1024];
                    int bytesRead = input.read(buffer);
                    String message = new String(buffer, 0, bytesRead);
                    System.out.println("Received message: " + message);
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            senderThread.start();
            receiverThread.start();

            senderThread.join();
            receiverThread.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
