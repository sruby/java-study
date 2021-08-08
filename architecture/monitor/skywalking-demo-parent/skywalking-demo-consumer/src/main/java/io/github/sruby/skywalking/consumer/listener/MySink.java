package io.github.sruby.skywalking.consumer.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

    String DEMO01_INPUT = "server-to-consumer-send";

    @Input(DEMO01_INPUT)
    SubscribableChannel demo01Input();

}
