package org.springframework.cloud.kubernetes.examples.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: user
 * @author: sruby
 * @create: 2020-12-08 15:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("T_USER")
public class User {
    private Integer id;
    private String name;
    private String userAuthority;
}
