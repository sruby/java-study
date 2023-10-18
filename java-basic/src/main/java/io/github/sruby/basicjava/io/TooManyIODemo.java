package io.github.sruby.basicjava.io;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import io.netty.util.CharsetUtil;

/**
 * mock too many io
 */
public class TooManyIODemo {
    public static void main(String[] args) {
        while (true){
            System.out.println(111);
//            FileUtil.writeString("hello","./test.txt", CharsetUtil.UTF_8);
            FileUtil.readString("2021年美团技术年货-20220120.pdf",CharsetUtil.UTF_8);
        }
    }
}
