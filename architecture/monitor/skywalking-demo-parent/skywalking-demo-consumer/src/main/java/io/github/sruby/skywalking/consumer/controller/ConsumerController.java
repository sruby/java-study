package io.github.sruby.skywalking.consumer.controller;

import io.github.sruby.skywalking.api.DemoClient;
import io.github.sruby.skywalking.dto.UserDTO;
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
public class ConsumerController {
    @Autowired
    private DemoClient demoClient;

    @GetMapping("/echo")
    public String echo(@RequestParam String param) throws InterruptedException {
        return demoClient.echo(param);
    }

    @GetMapping("/hello")
    public String hello() throws InterruptedException{
        return demoClient.hello();
    }

    @GetMapping("/saveAndGetDB")
    public List<UserDTO> listById(){
        List<UserDTO> userDTOS = demoClient.saveAndGetDB();
        return userDTOS;
    }
}
