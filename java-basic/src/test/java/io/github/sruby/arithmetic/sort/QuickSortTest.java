package io.github.sruby.arithmetic.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @date 2020/7/17 11:07
 */
@Slf4j
public class QuickSortTest {
    private QuickSort quickSort = new QuickSort();
    @Test
    public void sortArray() {
        int[] array = new int[]{1,8,9,3,0,1,1,1,1,222};
        array = quickSort.sortArray(array);
        log.debug("array:{}",array);
    }
}