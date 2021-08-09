package io.github.sruby.skywalking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/demo")
public class DemoController{

    @GetMapping("/echo")
    public String echo(@RequestParam String param) throws InterruptedException {
        Random random = new Random();
        int timeout = random.nextInt(10000);
        TimeUnit.MILLISECONDS.sleep(timeout);
        if (random.nextInt(3)==1){
            throw new RuntimeException();
        }
        return "echo";
    }

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
//        int timeout = new Random().nextInt(10000);
//        TimeUnit.MILLISECONDS.sleep(timeout);
        return "hello";
    }

}
