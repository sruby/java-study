package org.springframework.cloud.kubernetes.examples.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.sruby.skywalking.dto.UserDTO;
import io.github.sruby.skywalking.message.Demo01Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.kubernetes.examples.entity.User;
import org.springframework.cloud.kubernetes.examples.mapper.UserMapper;
import org.springframework.cloud.kubernetes.examples.message.MySource;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Resource(name = "redisTemplate")
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ValueOperations<String, List<UserDTO>> operations;

    @Autowired
    private MySource mySource;

    public List<UserDTO> listById(Integer id){
        List<User> userList = userMapper.selectList(Wrappers.<User>lambdaQuery().eq(User::getId,id));
//        modelMapper.map(user, new TypeToken<List<UserDTO>>() {}.getType());

        List<UserDTO> dtos = userList
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        String k = "swdemo:user:list";
        operations.get(k);
        operations.set(k,dtos);

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
        // 发送消息
        return mySource.demo01Output().send(springMessage);
    }
}
