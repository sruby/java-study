package io.github.sruby.basicjava.threadlocal;

import io.github.sruby.spring.dao.po.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserCache {
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static User getUser() {
        User user = userThreadLocal.get();
        if (user == null) {
            user = getUserFromRedis();
            userThreadLocal.set(user);
        }
        return user;
    }

    private static User getUserFromRedis() {
        log.info("getUserFromRedis");

        // 从 Redis 中获取用户信息的逻辑
        // ...
        User user = new User();
        user.setUserName("zhangsan");
        return user;
    }
}