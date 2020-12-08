package io.sruby.github.test.unit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: ipo
 * @author: sruby
 * @create: 2020-12-07 11:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ipo {
    private Integer id;
    private String code;
    private String companyId;
}
