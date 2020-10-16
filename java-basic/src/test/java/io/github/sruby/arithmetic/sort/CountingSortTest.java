package io.github.sruby.arithmetic.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @date 2020/8/4 16:33
 */
public class CountingSortTest {
    private CountingSort countingSort = new CountingSort();

    @Test
    public void sortArray() {
        int[] nums = new int[]{12,22,11,8,29,29,20};
        nums = countingSort.sortArray(nums);
        for(int num:nums){
            System.out.println(num);
        }
    }
}