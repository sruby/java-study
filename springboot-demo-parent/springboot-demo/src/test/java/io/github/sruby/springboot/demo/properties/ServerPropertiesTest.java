package io.github.sruby.springboot.demo.properties;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @date 2020/10/19 14:36
 */
@SpringBootTest
@Slf4j
class ServerPropertiesTest {

    @Value("${server.name}")
    private String name;
    @Value("${server.port}")
    private String port;
    @Value("${server.ip}")
    private String ip;
    @Test
    public void test() {
        log.info("name:{},ip:{},port:{}",name,ip,port);
    }

}