package io.github.sruby.skywalking.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/consumer/{segment}")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://127.0.0.1:8089"))
//                .route(p -> p
//                        .host("*.circuitbreaker.com")
//                        .filters(f -> f.circuitBreaker(config -> config.setName("mycmd")))
//                        .uri("http://127.0.0.1:8089"))
                .build();
    }
}
