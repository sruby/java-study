package io.github.sruby.designpattern.proxy.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author sruby on 2020-11-21 10:02
 */
@Slf4j
class DynamicAgentTest {


    @Test
    void testCreateProxy() {
        IDbQuery result = (IDbQuery) DynamicAgent.createProxy(IDbQuery.class, new IDbQueryImpl());
        result.query();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme