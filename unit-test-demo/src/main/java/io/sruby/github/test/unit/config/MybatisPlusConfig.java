package io.sruby.github.test.unit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("io.sruby.github.test.unit.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisPlusConfig {

}
