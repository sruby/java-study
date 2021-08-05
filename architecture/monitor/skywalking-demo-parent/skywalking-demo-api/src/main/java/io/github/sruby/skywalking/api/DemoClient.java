package io.github.sruby.skywalking.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: demo client
 * @author: sruby
 * @create: 2021-08-04 15:13
 */
@FeignClient(name = "skywalking-demo-server")
@RequestMapping("/demo")
public interface DemoClient {
    @GetMapping("/echo")
    public String echo(@RequestParam("param") String param) throws InterruptedException;

    @GetMapping("/hello")
    public String hello() throws InterruptedException;
}
