package io.sruby.github.skywalking.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("io.github.sruby.skywalking.mapper")
public class MybatisPlusConfig {

}
