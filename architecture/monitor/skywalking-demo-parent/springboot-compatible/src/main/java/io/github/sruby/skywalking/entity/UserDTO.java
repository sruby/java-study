package io.github.sruby.skywalking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @description: user
 * @author: sruby
 * @create: 2020-12-08 15:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDTO {
    private Integer id;
    private String name;
    private String userAuthority;
}
