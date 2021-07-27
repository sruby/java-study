package cn.iocoder.springcloud.labx03.feigndemo.consumer.controller;

import cn.iocoder.springcloud.labx03.feigndemo.consumer.feign.DemoProviderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class ConsumerController {

    @Autowired
    private DemoProviderFeignClient demoProviderFeignClient;

    @GetMapping("/hello02")
    public String hello02(String name) {
        // 使用 Feign 调用接口
        String response = demoProviderFeignClient.echo(name);
        // 返回结果
        return "consumer:" + response;
    }

    @GetMapping("/privider-read-timeout")
    public String helloTimeout(String name) {
        String response = demoProviderFeignClient.echoTimeout(name);
        // 返回结果
        return "consumer:" + response;
    }

    @GetMapping("/consumer-timeout")
    public String consumerTimeout(String name) throws InterruptedException {
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
        // 返回结果
        return "consumerTimeout" ;
    }

}
