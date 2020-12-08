package io.sruby.github.test.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @description: application
 * @author: sruby
 * @create: 2020-12-07 12:01
 */
@SpringBootApplication
public class UnitTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnitTestApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
