package io.github.sruby.concurrent.pipe;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 使用 PipedWriter 和 PipedReader 创建了一个字符流的管道。
 * senderThread 线程向管道中写入字符流数据，
 * 而 receiverThread 线程从管道中读取字符流数据。通过管道的连接，实现了线程间的数据交换。
 */
public class CharPipeExample {
    public static void main(String[] args) {
        try {
            final PipedWriter writer = new PipedWriter();
            final PipedReader reader = new PipedReader(writer);

            Thread senderThread = new Thread(() -> {
                try {
                    String message = "Hello, World!";
                    writer.write(message);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread receiverThread = new Thread(() -> {
                try {
                    char[] buffer = new char[1024];
                    int charsRead = reader.read(buffer);
                    String message = new String(buffer, 0, charsRead);
                    System.out.println("Received message: " + message);
                    reader.close();
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
