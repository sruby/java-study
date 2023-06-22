package io.github.sruby.springboot.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api(tags = "首页模块")
@RestController
@Slf4j
public class IndexController {

    @ApiImplicitParam(name = "name",value = "姓名",required = true)
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public ResponseEntity<Map<String, String>> sayHi(@RequestParam(value = "name")String name) throws InterruptedException {
        log.info("current thread name:{}",Thread.currentThread().getName());
        /**
         * sleep 1 min
         */
        TimeUnit.SECONDS.sleep(60);


        String result = "Hi:" + name;
        Map<String,String> mapResult = new HashMap<>();
        mapResult.put("data",result);
        return ResponseEntity.ok(mapResult);
    }
}
