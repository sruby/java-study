package org.springframework.cloud.kubernetes.examples.controller;

import io.github.sruby.skywalking.api.DemoClient;
import io.github.sruby.skywalking.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.kubernetes.examples.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class DemoController implements DemoClient {
    @Autowired
    private UserService userService;

    @Override
    public String echo(@RequestParam String param) throws InterruptedException {
        Random random = new Random();
        int timeout = random.nextInt(10000);
        TimeUnit.MILLISECONDS.sleep(timeout);
        if (random.nextInt(3)==1){
            throw new RuntimeException();
        }
        return "echo";
    }

    @Override
    public String hello() throws InterruptedException {
//        int timeout = new Random().nextInt(10000);
//        TimeUnit.MILLISECONDS.sleep(timeout);
        return "hello";
    }

    @Override
    public String getDataFromRedis() {

        return null;
    }

    @Override
    public String produceMeg() {
        return null;
    }

    @Override
    public String consumerMeg() {
        return null;
    }

    @Override
    public List<UserDTO> saveAndGetDB() {
        Integer id = 1;
        userService.insert(UserDTO.builder().id(id).name("test").userAuthority("all").build());
        List<UserDTO> user = userService.listById(id);
        return user;
    }

}
