package io.github.sruby.springboot.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.ArrayList;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://spring.io/guides/gs/testing-web/
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EncodingControllerTest {
    @Autowired
    private EncodingController encodingController;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Test
    public void contextLoads() {
        assertThat(encodingController).isNotNull();
    }

    @Test
    void testGreeting() {
        String parameter = "我们";
        String url = "http://localhost:" + port + "/greeting";
        String result = testRestTemplate.getForObject(url, String.class, parameter);
        log.info("parameter:{}",parameter);
        assertThat(result).contains(parameter);
    }

    @Test
    public void testGreeting_withParameter(){
        String parameter = "我们";
        String url = "http://localhost:" + port + "/greeting";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", parameter);
        HttpHeaders headers = new HttpHeaders();
        ArrayList<Charset> charsetArrayList = new ArrayList<>();
        charsetArrayList.add(UTF_8);
        headers.setAcceptCharset(charsetArrayList);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> result = testRestTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        log.info("result:{}",result);
        assertThat(result.getBody().toString()).contains(parameter);
    }

    @Test
    public void testGreeting_withParameter_ISO_8859_1(){
        String parameter = "联通";
        String url = "http://localhost:" + port + "/greeting";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", parameter);
        HttpHeaders headers = new HttpHeaders();
        ArrayList<Charset> charsetArrayList = new ArrayList<>();
        //表示客户端可以接受的字符集，而不是指定请求的字符集
        charsetArrayList.add(ISO_8859_1);
        headers.setAcceptCharset(charsetArrayList);
        Charset charset = Charset.forName("ISO_8859_1");
        MediaType mediaType = new MediaType("text", "plain", charset);
        headers.setContentType(mediaType);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> result = testRestTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
        log.info("result:{}",result);
        assertThat(result.getBody().toString()).contains(parameter);
    }
}