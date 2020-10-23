package io.github.sruby.springboot.autoconfigure;

import io.github.sruby.sdk.ConfigProvide;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @date 2020/10/19 17:01
 */
@Configuration
@ConditionalOnClass(ConfigProvide.class)
@EnableConfigurationProperties(DemoProperties.class)
public class DemoAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ConfigProvide configProvide(){
        return new SpringConfigProvide();
    }

    @Bean
    public StaticContextHolderInAutoConfigure staticContextHolderInAutoConfigure(){
        return new StaticContextHolderInAutoConfigure();
    }
}
