package io.github.sruby.springboot.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "server")
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