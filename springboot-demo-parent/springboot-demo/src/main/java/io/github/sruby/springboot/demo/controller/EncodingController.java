package io.github.sruby.springboot.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: encoding
 * @author: sruby
 * @create: 2021-06-17 18:34
 */
@RestController
@Slf4j
public class EncodingController {
    @RequestMapping(value = "/greeting", produces = "text/plain;charset=ISO_8859_1")
    public String greeting(@RequestParam(value = "name", required = false,
            defaultValue = "world") String name, Model model)
    {
        log.info("name:{}", name);
        model.addAttribute("name", name);
        return name;
    }
}
