package io.github.sruby.springboot.kafka.demo;

import org.springframework.kafka.annotation.KafkaListener;

/**
 * Consuming  Messages
 *
 * @author sruby on
 */
public class ConsumingMessages {
    @KafkaListener(topics = KafkaTopicConfig.NEW_TOPIC, groupId = "")
    public void listen(String message) {
        System.out.println("Received Messasge in group foo: " + message);
    }
}
