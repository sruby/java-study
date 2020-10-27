package io.github.sruby.sdk;

import com.alibaba.fastjson.JSON;

/**
 * @date 2020/10/20 16:56
 */
public class JsonDemo {

    public void test() {
        JSON json = JSON.parseObject("{a:1}",JSON.class);
        System.out.println("json:"+json.toJSONString());
    }
}
