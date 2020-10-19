package io.github.sruby.springboot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @date 2020/10/19 16:46
 */
@ConfigurationProperties("demo")
public class DemoProperties {
    /**
     * demo name
     */
    private String name;
}
