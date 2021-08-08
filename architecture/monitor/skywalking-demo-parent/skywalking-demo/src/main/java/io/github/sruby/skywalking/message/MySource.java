package io.github.sruby.skywalking.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {

    @Output("server-to-consumer-send")
    MessageChannel demo01Output();

}
