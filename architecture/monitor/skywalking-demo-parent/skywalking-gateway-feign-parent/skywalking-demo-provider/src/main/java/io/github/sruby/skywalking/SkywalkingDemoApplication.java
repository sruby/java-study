package io.github.sruby.skywalking;

import io.github.sruby.skywalking.message.MySource;
import org.modelmapper.ModelMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

/**
 * VM:
 * -javaagent:/Users/macuser/soft/apache-skywalking-apm-bin-es7/agent/skywalking-agent.jar=agent.instance_name=demo-application-local,agent.namespace=sruby-namespace
 * ENV:
 * SW_AGENT_NAME=demo-application;SW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800;SW_AGENT_SPAN_LIMIT=2000;SW_NAMESPACE=sruby-demo;SW_SEARCHABLE_TAG_KEYS=http.method,status_code,db.type,db.instance,mq.queue,mq.topic,mq.broker,tag.demo
 */
@SpringBootApplication
@MapperScan("io.github.sruby.skywalking.mapper")
@EnableBinding(MySource.class)
public class SkywalkingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkywalkingDemoApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
