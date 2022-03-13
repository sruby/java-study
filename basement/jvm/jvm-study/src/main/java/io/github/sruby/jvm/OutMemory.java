package io.github.sruby.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OutMemory {
    /**
     * 增加一下参数导致java.lang.OutOfMemoryError
     * -Xmx50M -XX:+PrintGCDetails
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.MINUTES.sleep(5);
        List<Byte[]> list=new ArrayList<Byte[]>();
        for(int i=0;i<100;i++){
            //构造1M大小的byte数值
            Byte[] bytes=new Byte[1024*1024];
            //将byte数组添加到list列表中，因为存在引用关系所以bytes数组不会被GC回收
            list.add(bytes);
        }
        System.out.println("test");
    }
}
