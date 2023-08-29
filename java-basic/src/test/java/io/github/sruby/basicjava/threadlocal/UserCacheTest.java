package io.github.sruby.basicjava.threadlocal;

import io.github.sruby.spring.dao.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Sruby
 * @date 8/29/2023 8:55 PM
 */
@Slf4j
class UserCacheTest {

    @Test
    void getUser() {
//        from db
        User user = UserCache.getUser();
        log.info("user：{}",user);
//        from threadlocal
        user = UserCache.getUser();
        log.info("user：{}",user);
    }
}