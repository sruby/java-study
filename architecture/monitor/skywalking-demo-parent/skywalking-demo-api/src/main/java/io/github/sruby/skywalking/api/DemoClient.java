package io.github.sruby.skywalking.api;

import io.github.sruby.skywalking.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @GetMapping("/getDataFromRedis")
    public String getDataFromRedis();

    @GetMapping("/produceMeg")
    public String produceMeg();

    @GetMapping("/consumerMeg")
    public String consumerMeg();

    @GetMapping("/saveAndGetDB")
    public List<UserDTO> saveAndGetDB();
}
