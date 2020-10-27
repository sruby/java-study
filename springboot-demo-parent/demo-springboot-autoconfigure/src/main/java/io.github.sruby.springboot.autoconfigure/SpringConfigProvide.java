package io.github.sruby.springboot.autoconfigure;

import io.github.sruby.sdk.ConfigProvide;
import org.springframework.beans.factory.annotation.Value;

/**
 * @date 2020/10/20 15:42
 */
public class SpringConfigProvide implements ConfigProvide {
    @Value("${demo.name}")
    private String name;

    @Override
    public String getValue() {
        return name;
    }
}
