package io.github.sruby.basicjava.io.nio;

import io.netty.channel.pool.FixedChannelPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO server demo
 * @author sruby
 */
public class BioServerDemo extends Thread{


    private ServerSocket serverSocket;

//    public void run(){
//        try {
//            serverSocket = new ServerSocket(0);
//            while (true){
//                Socket socket = serverSocket.accept();
//                RequestHandler requestHandler = new RequestHandler(socket);
//                requestHandler.start();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * 重写run方法
     * 线程池
     */
    public void run(){
        try {
            serverSocket = new ServerSocket(0);
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            while (true){
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                executorService.execute(requestHandler);
//                requestHandler.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPort(){
        return serverSocket.getLocalPort();
    }

    class RequestHandler extends Thread{
        private Socket socket;

        public RequestHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            // do something
            try(PrintWriter printWriter = new PrintWriter(socket.getOutputStream())){
                // write to client
                // socket.getOutputStream().write();
                printWriter.println("hello world");
                printWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public static void main(String[] args) {
        BioServerDemo bioServerDemo = new BioServerDemo();
        bioServerDemo.start();

        try (Socket client = new Socket(InetAddress.getLocalHost(),bioServerDemo.getPort())){
            new BufferedReader(new java.io.InputStreamReader(client.getInputStream()))
                    .lines().forEach(System.out::println);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
