package io.github.sruby.skywalking.service;

import io.github.sruby.skywalking.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class UserServiceTest {
    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void listById() {
        List<UserDTO> userDTOS = userService.listById(1);
        log.info("userDOTS:{}",userDTOS);
    }

    @Test
    void insert() {
        int insert = userService.insert(UserDTO.builder().id(1)
                .name("test")
                .userAuthority("all")
                .build());
    }
}