package io.github.sruby.springboot.demo.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @date 2020/10/23 10:29
 *
 */
@Component
@Slf4j
public class ReadValueFromPropertiesService {
    /**
     * 1、use @value
     */
    @Value("${server.name}")
    private String name;

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private Environment environment;

    public void read(){
        /**
         * 2、use @ConfigurationProperties
         */
        String name2 = serverProperties.getName();

        /**
         * 3、 use Environment object
         */
        String name3 = environment.getProperty("server.name");

        log.info("name:{},name2:{},name3:{}",name,name2,name3);
    }
}
