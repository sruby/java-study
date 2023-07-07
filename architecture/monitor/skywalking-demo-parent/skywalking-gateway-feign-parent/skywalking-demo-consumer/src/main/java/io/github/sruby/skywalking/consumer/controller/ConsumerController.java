package io.github.sruby.skywalking.consumer.controller;

import io.github.sruby.skywalking.api.DemoClient;
import io.github.sruby.skywalking.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: consumer controller
 * @author: sruby
 * @create: 2021-08-04 20:31
 */
@RestController
@RequestMapping("/consumer")
@Slf4j
public class ConsumerController {
    @Autowired
    private DemoClient demoClient;

    @GetMapping("/echo")
    public String echo(@RequestParam String param) throws InterruptedException {
        log.info("echo");
        return demoClient.echo(param);
    }

    @GetMapping("/hello")
    public String hello() throws InterruptedException{
        log.info("hello");
        return demoClient.hello();
    }

    @GetMapping("/saveAndGetDB")
    public List<UserDTO> listById(){
        log.info("saveAndGetDB");
        List<UserDTO> userDTOS = demoClient.saveAndGetDB();
        return userDTOS;
    }

    @GetMapping("/localecho")
    public String localEcho(String param){
        log.info("consumer local echo");
        return "consumer local echo";
    }
}
