package io.github.sruby.basicjava.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @description: test
 * @author: sruby
 * @create: 2021-01-25 10:44
 */
@Slf4j
public class StringTest {
    @Test
    public void test(){
      log.debug("0x01");
      byte[] bytes = {0x01};
      log.debug(new String(bytes));
      char c = '\01';
      log.debug(String.valueOf(c));
    }
}
