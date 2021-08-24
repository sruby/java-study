package io.sruby.github.test.unit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: ipo
 * @author: sruby
 * @create: 2020-12-07 15:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private String code;
    private String companyId;
    private String companyName;
}
