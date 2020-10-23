package io.github.sruby.springboot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @date 2020/10/19 16:46
 */
@ConfigurationProperties("demo")
@Data
public class DemoProperties extends DemoParentProperties{
    /**
     * demo name
     */
    private String name;
}
