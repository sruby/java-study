package io.github.sruby.springboot.demo;

import io.github.sruby.sdk.ConfigProvide;
import io.github.sruby.springboot.autoconfigure.DemoProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @date 2020/10/20 16:37
 */
@SpringBootTest
@Slf4j
public class ConfigProvideTest {
    @Autowired
    private ConfigProvide configProvide;
    @Autowired
    private DemoProperties demoProperties;
    @Test
    public void getValue() {
        String value = configProvide.getValue();

        log.info("value:{},valurFromGet:{}",value,demoProperties.getName());

    }

}
