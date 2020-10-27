package io.github.sruby.springboot.demo.service.impl;

import io.github.sruby.springboot.demo.service.ISpringServbice;
import lombok.extern.slf4j.Slf4j;

/**
 * @date 2020/10/26 10:52
 */
@Slf4j
public class SpringServbiceImpl implements ISpringServbice {
    private String key;
    @Override
    public void out() {
        log.info("spring service out:{}",key);
    }
}
