package io.github.sruby.basicjava.io.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * client demo
 *
 * @author Sruby
 * @date 27/6/2023 15:44
 */
public class ClientDemo {
    public static final int PORT = 8888;

    public static void main(String[] args) {
        try (Socket client = new Socket(InetAddress.getLocalHost(),PORT)){
            new BufferedReader(new java.io.InputStreamReader(client.getInputStream()))
                    .lines().forEach(System.out::println);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
