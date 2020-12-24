package io.sruby.github.test.unit.parameter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class StateInfo {
    private String stateCode;
    private Integer val1;
    private Integer val2;
}