package io.github.sruby.skywalking.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.sruby.skywalking.entity.User;
import io.github.sruby.skywalking.entity.UserDTO;
import io.github.sruby.skywalking.mapper.UserMapper;
import io.github.sruby.skywalking.message.Demo01Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> listById(Integer id){
        List<User> userList = userMapper.selectList(Wrappers.<User>lambdaQuery().eq(User::getId,id));
//        modelMapper.map(user, new TypeToken<List<UserDTO>>() {}.getType());

        List<UserDTO> dtos = userList
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        String k = "swdemo:user:list";

        send();

        return dtos;
    }

    public int insert(UserDTO user){
        User userEntity = modelMapper.map(user, User.class);
        return userMapper.insert(userEntity);
    }

    public boolean send() {
        // 创建 Message
        Demo01Message message = new Demo01Message()
                .setId(new Random().nextInt());
        // 创建 Spring Message 对象
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                .build();
        return true;
    }
}
