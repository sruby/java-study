package io.sruby.github.test.unit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("io.sruby.github.test.unit.mapper")
public class MybatisPlusConfig {

}
