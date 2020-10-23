package io.github.sruby.springboot.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "server")
@Component
@Data
public class ServerProperties {
    /**
     * Name of the server.
     */
    private String name;
    /**
     * IP address to listen to.
     */
    private String ip = "127.0.0.1";
    /**
     * Port to listener to.
     */
    private int port = 9797;
}