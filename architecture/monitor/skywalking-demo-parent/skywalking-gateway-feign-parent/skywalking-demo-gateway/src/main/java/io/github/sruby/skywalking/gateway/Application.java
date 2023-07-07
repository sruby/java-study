package io.github.sruby.skywalking.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


/**
 * VM:
 * -javaagent:/Users/macuser/soft/apache-skywalking-java-agent-8.16.0/skywalking-agent.jar=agent.instance_name=demo-gateway,agent.namespace=sruby-namespace,agent.keep_tracing=true
 * ENV:
 * SW_AGENT_NAME=demo-application;SW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800;SW_AGENT_SPAN_LIMIT=2000;SW_NAMESPACE=sruby-demo;SW_SEARCHABLE_TAG_KEYS=http.method,status_code,db.type,db.instance,mq.queue,mq.topic,mq.broker,tag.demo
 */
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
