package io.github.sruby.skywalking.api;

import io.github.sruby.skywalking.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description: demo client
 * @author: sruby
 * @create: 2021-08-04 15:13
 */
@FeignClient(name = "skywalking-demo-server",url = "127.0.0.1:8079")
//@RequestMapping("/demo")
public interface DemoClient {
    @GetMapping("/demo/echo")
    public String echo(@RequestParam("param") String param) throws InterruptedException;

    @GetMapping("/demo/hello")
    public String hello() throws InterruptedException;

    @GetMapping("/demo/getDataFromRedis")
    public String getDataFromRedis();

    @GetMapping("/demo/produceMeg")
    public String produceMeg();

    @GetMapping("/demo/consumerMeg")
    public String consumerMeg();

    @GetMapping("/demo/saveAndGetDB")
    public List<UserDTO> saveAndGetDB();
}
