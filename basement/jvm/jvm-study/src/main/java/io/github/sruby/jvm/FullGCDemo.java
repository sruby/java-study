package io.github.sruby.jvm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * full gc demo
 *  -Xmx50M -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xloggc:./fullgcdemo-gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./fullgcdemo-dump.hprof
 *  old generation: 50/3*2=25M
 * @author sruby
 */
public class FullGCDemo {


    @Test
    public void testOutMemory(){
        List<Byte[]> list=new ArrayList<Byte[]>();
        for(int i=0;i<25;i++){
            //构造1M大小的byte数值
            Byte[] bytes=new Byte[1024*1024];
            //将byte数组添加到list列表中，因为存在引用关系所以bytes数组不会被GC回收
            list.add(bytes);
        }
        System.out.println("test");
    }
}
