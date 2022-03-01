package io.github.sruby.skywalking.consumer;

import io.github.sruby.skywalking.consumer.listener.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication(scanBasePackages = "io.github.sruby.skywalking.**")
@EnableFeignClients(basePackages = "io.github.sruby.skywalking.api")
@EnableBinding(MySink.class)
public class SkywalkingDemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkywalkingDemoConsumerApplication.class, args);
    }

}
