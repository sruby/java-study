package io.github.sruby.javaguide.basic.collection.ist;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2020/8/13 15:48
 */
@Slf4j
public class ArrayListTest {
    @Test
    public void test() {
        List<Integer> list =new ArrayList();
        list.add(1);

        for(Integer integer : list){
            log.debug("integer:{}",integer);
        }
    }

    @Test
    public void test_full() {
        List<Integer> list =new ArrayList(2);
        list.add(1);
        list.add(2);
        list.add(3);
    }
}
