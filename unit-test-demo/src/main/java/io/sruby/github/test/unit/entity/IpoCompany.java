package io.sruby.github.test.unit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: user
 * @author: sruby
 * @create: 2020-12-07 11:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IpoCompany {
    private Integer id;
    private String companyId;
    private String companyName;
}
