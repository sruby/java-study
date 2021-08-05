package io.github.sruby.skywalking.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "io.github.sruby.skywalking.**")
@EnableFeignClients(basePackages = "io.github.sruby.skywalking.api")
public class SkywalkingDemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkywalkingDemoConsumerApplication.class, args);
    }

}
