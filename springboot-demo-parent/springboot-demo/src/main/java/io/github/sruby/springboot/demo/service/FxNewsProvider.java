package io.github.sruby.springboot.demo.service;

import lombok.Data;

/**
 * @author sruby
 * @date 2020/10/28 11:28
 */
@Data
public class FxNewsProvider {
    private DowJonesNewsListener dowJonesNewsListener;
    private DowjonesNewsPersister dowjonesNewsPersister;
}
