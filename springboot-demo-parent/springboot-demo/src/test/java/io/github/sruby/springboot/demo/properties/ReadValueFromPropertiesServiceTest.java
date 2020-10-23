package io.github.sruby.springboot.demo.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @date 2020/10/23 10:34
 */
@SpringBootTest
class ReadValueFromPropertiesServiceTest {
    @Autowired
    private ReadValueFromPropertiesService readValueFromPropertiesService;

    @Test
    void read() {
        readValueFromPropertiesService.read();
    }
}