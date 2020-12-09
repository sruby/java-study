package io.sruby.github.test.unit.client;

import io.sruby.github.test.unit.entity.User;

/**
 * @description: user client
 * @author: sruby
 * @create: 2020-12-08 15:52
 */
public class UserClient {
    User getUserInfo(){
        return User.builder().id(1).name("sruby").userAuthority("all").build();
    }
}
