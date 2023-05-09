package io.github.sruby.security.encryption;

import com.qcloud.cos.utils.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class MD5Test {

    @Test
    public void test() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String md5Hex = Md5Utils.md5Hex(timestamp + "@JHvPLJX0iDG5KqXcrJSfUuRjBOUs1FwC" );
        log.info("timestamp:{},md5Hex:{}",timestamp,md5Hex);
    }
}
