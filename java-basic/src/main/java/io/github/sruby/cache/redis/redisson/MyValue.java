package io.github.sruby.cache.redis.redisson;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sruby on 2020-12-3 23:25
 */
@Data
@Builder
public class MyValue implements Serializable {
    private static final long serialVersionUID = 1L;
    private String myValue;
}
