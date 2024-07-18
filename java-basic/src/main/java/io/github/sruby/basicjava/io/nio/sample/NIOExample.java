package io.github.sruby.basicjava.io.nio.sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOExample {
    private static final int PORT = 5000;
    private static final String HOST = "localhost";
    private static volatile boolean serverReady = false;

    public static void main(String[] args) {
        Thread serverThread = new Thread(NIOExample::startServer);
        Thread clientThread = new Thread(NIOExample::startClient);

        serverThread.start();
        clientThread.start();

        try {
            serverThread.join();
            clientThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startServer() {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(HOST, PORT));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer buffer = ByteBuffer.allocate(256);

            System.out.println("Server started on " + HOST + ":" + PORT);
            serverReady = true;

            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();

                    if (key.isAcceptable()) {
                        register(selector, serverSocket);
                    }

                    if (key.isReadable()) {
                        answerWithEcho(buffer, key);
                    }

                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        System.out.println("New client connected: " + client.getRemoteAddress());
    }

    private static void answerWithEcho(ByteBuffer buffer, SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        int bytesRead = client.read(buffer);
        if (bytesRead == -1) {
            key.cancel();
            client.close();
            System.out.println("Client disconnected: " + client.getRemoteAddress());
            return;
        }
        buffer.flip();
        client.write(buffer);
        buffer.clear();
    }

    private static void startClient() {
        while (!serverReady) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            SocketChannel client = SocketChannel.open();
            if (client.connect(new InetSocketAddress(HOST, PORT))) {
                ByteBuffer buffer = ByteBuffer.allocate(256);

                String message = "Hello, NIO Server!";
                buffer.put(message.getBytes());
                buffer.flip();
                client.write(buffer);
                buffer.clear();

                int bytesRead = client.read(buffer);
                buffer.flip();
                byte[] responseBytes = new byte[bytesRead];
                buffer.get(responseBytes);
                String response = new String(responseBytes);
                System.out.println("Server response: " + response);

                client.close();
            }
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}