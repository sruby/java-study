package org.springframework.cloud.kubernetes.examples.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: bean config
 * @author: sruby
 * @create: 2021-08-30 09:03
 */
@Configuration
public class BeanConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
