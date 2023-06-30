package io.github.sruby.basicjava.io.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO server demo
 *
 * @author Sruby
 * @date 26/6/2023 15:47
 */
public class NioServerDemo extends Thread{

    public static final int PORT = 8888;

    public void run(){
        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();){ // 创建Selector和Channel
            serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), PORT));
            serverSocketChannel.configureBlocking(false);
            //     共有 4 种事件类型，分别是：（任何网络和文件操作，都可以抽象成这四个事件。)
            //*   新连接事件（OP_ACCEPT）；
            //*   连接就绪事件（OP_CONNECT）；
            //*   读就绪事件（OP_READ）；
            //*   写就绪事件（OP_WRITE）
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true){
                selector.select(); // 阻塞等待就绪的Channel，这是关键点之一
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey events = iterator.next();
                    sayHelloWorld((ServerSocketChannel) events.channel());
                    iterator.remove();
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void sayHelloWorld(ServerSocketChannel channel) {
        try (SocketChannel socketChannel = channel.accept();){
            socketChannel.write(Charset.defaultCharset().encode("Hello World"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        NioServerDemo nioServerDemo = new NioServerDemo();
        nioServerDemo.start();
    }
}
