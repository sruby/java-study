package io.github.sruby.springboot.demo.service.impl;

import io.github.sruby.springboot.demo.service.ISpringService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @date 2020/10/26 10:52
 */
@Slf4j
@Data
public class SpringServiceImpl implements ISpringService {
    private String key;
    @Override
    public void out() {
        log.info("spring service out:{}",key);
    }
}
