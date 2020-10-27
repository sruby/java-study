package io.github.sruby.designpattern.proxy.dynamic;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @date 2020/10/23 17:19
 */
class DbQueryHandlerTest {
    private DbQueryHandler dbQueryHandler = new DbQueryHandler();

    @Test
    void createProxy() {
        IDbQuery proxy = dbQueryHandler.createProxy();
        String query = proxy.query();
        Assert.assertEquals(query,"queryproxy");
    }
}