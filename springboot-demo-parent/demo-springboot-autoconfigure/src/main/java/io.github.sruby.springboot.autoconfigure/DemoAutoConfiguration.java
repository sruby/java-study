package io.github.sruby.springboot.autoconfigure;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @date 2020/10/19 17:01
 */
@Configuration
@ConditionalOnClass(JSON.class)
@EnableConfigurationProperties(DemoProperties.class)
public class DemoAutoConfiguration {

}
