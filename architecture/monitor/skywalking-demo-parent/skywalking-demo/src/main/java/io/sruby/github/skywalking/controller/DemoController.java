package io.sruby.github.skywalking.controller;

import io.github.sruby.skywalking.api.DemoClient;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
//@RequestMapping("/demo")
public class DemoController implements DemoClient {

//    @GetMapping("/echo")
//    @Trace(operationName = "trace_annotations")
//    @Tag(key = "tag.demo.a",value = "1")
//    @Tag(key = "tag.demo",value = "arg[0]")
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

//    @GetMapping("/hello")
    @Override
    public String hello() throws InterruptedException {
//        int timeout = new Random().nextInt(10000);
//        TimeUnit.MILLISECONDS.sleep(timeout);
        return "hello";
    }

}
