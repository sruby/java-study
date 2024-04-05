package io.github.sruby.springboot.demo.controller;

import io.github.sruby.springboot.demo.dto.EventData;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.fasterxml.jackson.databind.ObjectMapper; // You need Jackson in the classpath

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class StreamController {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(path = "/stream", produces = MediaType.APPLICATION_JSON_VALUE)
    public SseEmitter streamSseMvc() {
        SseEmitter emitter = new SseEmitter();

        executor.execute(() -> {
            try {
                for (int i = 0; true; i++) {
                    EventData eventData = new EventData("event-" + i, i);
                    String json = objectMapper.writeValueAsString(eventData);
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data(json, MediaType.APPLICATION_JSON)
                            .id(String.valueOf(i))
                            .name("message");
                    emitter.send(event);
                    Thread.sleep(1000); // simulate delay
                }
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }
}