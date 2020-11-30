package io.github.sruby.collection.set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class TestSet {

    public static void main(String[] args) {

        Set<String> result = new HashSet<String>();
        Set<String> set1 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("英雄联盟");
                add("穿越火线");
                add("地下城与勇士");
            }
        };

        Set<String> set2 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("地下城与勇士");
                add("魔兽世界");
            }
        };

        result.clear();
        result.addAll(set1);
        result.retainAll(set2);
        System.out.println("交集：" + result);

        result.clear();
        result.addAll(set1);
        result.removeAll(set2);
        System.out.println("差集：" + result);

        result.clear();
        result.addAll(set1);
        result.addAll(set2);
        System.out.println("并集：" + result);
    }

    @Test(expected = NullPointerException.class)
    public void retainAll_NullPointerException() {
        Set<String> set1 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("英雄联盟");
                add("穿越火线");
                add("地下城与勇士");
            }
        };
        Set<String> set2 = null;

        set1.retainAll(set2);
        log.info("set1:{}", set1);
    }

    @Test(expected = NullPointerException.class)
    public void retainAll_NullPointerException2() {
        Set<String> set1 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("英雄联盟");
                add("穿越火线");
                add("地下城与勇士");
            }
        };
        Set<String> set2 = null;

        set2.retainAll(set1);
        log.info("set1:{}", set1);
    }

//    @Test(expected = UnsupportedOperationException.class)
    @Test
    public void retainAll_set_list() {
        Set<String> set1 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("英雄联盟");
                add("穿越火线");
                add("地下城与勇士");
            }
        };
        List<String> list = Arrays.asList(new String[]{"王者荣耀","王者荣耀","aaaa"});
        set1.retainAll(list);
        log.info("set1:{}", set1);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void retainAll_UnsupportedOperationException() {
        Set<String> set1 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("英雄联盟");
                add("穿越火线");
                add("地下城与勇士");
            }
        };
        List<String> list = Arrays.asList(new String[]{"王者荣耀","王者荣耀","aaaa"});
        //会对list做remove，而aslist转换而来的不支持
        list.retainAll(set1);
        log.info("set1:{}", set1);
    }
    @Test
    public void retainAll_arrays_stream() {
        Set<String> set1 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("英雄联盟");
                add("穿越火线");
                add("地下城与勇士");
            }
        };
        List<String> list = Arrays.stream(new String[]{"王者荣耀","王者荣耀","aaaa"}).collect(Collectors.toList());
        //会对list做remove，而aslist转换而来的不支持
        list.retainAll(set1);
        log.info("set1:{}", list);
    }

}