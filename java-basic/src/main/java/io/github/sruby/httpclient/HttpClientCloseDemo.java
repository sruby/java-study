package io.github.sruby.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @date 2020/9/17 15:29
 */
@Slf4j
public class HttpClientCloseDemo {
    private static CloseableHttpClient httpClient;

    static {
        httpClient = HttpClients.custom().setMaxConnPerRoute(50).evictIdleConnections(60, TimeUnit.SECONDS).build();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                log.info("close httpclient");
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    public static void main(String[] args) {
        try {
            CloseableHttpResponse response = httpClient.execute(new HttpGet("http://www.baidu.com"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
