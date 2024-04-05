package io.github.sruby.springboot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventData {
    private String message;
    private long id;

    // Constructor, getters and setters
}