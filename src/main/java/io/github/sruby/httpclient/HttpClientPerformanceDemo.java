package io.github.sruby.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @date 2020/9/23 15:02
 */
@Controller
@Slf4j
public class HttpClientPerformanceDemo {

    @GetMapping("wrong1")
    public String wrong1() {
        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(new PoolingHttpClientConnectionManager())
                .evictIdleConnections(80, TimeUnit.SECONDS)
                .setConnectionTimeToLive(70, TimeUnit.SECONDS)
                .build();
        HttpGet request = new HttpGet("http://127.0.0.1:45678/httpclientnotreuse/test");
        request.setProtocolVersion(HttpVersion.HTTP_1_1);
        try (CloseableHttpResponse response = client.execute(request)) {
            return EntityUtils.toString(response.getEntity());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("wrong2")
    public String wrong2() {
        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(new PoolingHttpClientConnectionManager())
//                .evictIdleConnections(60, TimeUnit.SECONDS)
                .build();
        HttpGet request = new HttpGet("http://127.0.0.1:45678/httpclientnotreuse/test");
        request.setProtocolVersion(HttpVersion.HTTP_1_1);
        try (CloseableHttpResponse response = client.execute(request)) {
            return EntityUtils.toString(response.getEntity());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/httpclientnotreuse/test")
    public String test() {
        log.info("===========test=============");
        return "test response";
    }


    private static CloseableHttpClient httpClient;

    static {
        //当然，也可以把CloseableHttpClient定义为Bean，然后在@PreDestroy标记的方法内close这个HttpClient
        httpClient = HttpClients.custom().setMaxConnPerRoute(1).setMaxConnTotal(1).evictIdleConnections(60, TimeUnit.SECONDS).build();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                httpClient.close();
            } catch (IOException ignored) {
            }
        }));
    }

    @GetMapping("right")
    public String right() {
        try (CloseableHttpResponse response = httpClient.execute(new HttpGet("http://127.0.0.1:45678/httpclientnotreuse/test"))) {
            return EntityUtils.toString(response.getEntity());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void setRequestConfig() throws IOException {
        HttpContext context = new BasicHttpContext();
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        HttpHost target = clientContext.getTargetHost();
        HttpRequest request = clientContext.getRequest();
        HttpResponse response = clientContext.getResponse();
        RequestConfig config = clientContext.getRequestConfig();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(1000)
                .setConnectTimeout(1000)
                .build();
        HttpGet httpget1 = new HttpGet("http://localhost/1");
        httpget1.setConfig(requestConfig);
        CloseableHttpResponse response1 = httpclient.execute(httpget1, context);
        try {
            HttpEntity entity1 = response1.getEntity();
        } finally {
            response1.close();
        }

        HttpGet httpget2 = new HttpGet("http://localhost/2");
        CloseableHttpResponse response2 = httpclient.execute(httpget2, context);
        try {
            HttpEntity entity2 = response2.getEntity();
        } finally {
            response2.close();
        }
    }

}
