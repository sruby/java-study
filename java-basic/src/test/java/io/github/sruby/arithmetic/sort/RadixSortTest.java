package io.github.sruby.arithmetic.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @date 2020/8/4 16:37
 */
public class RadixSortTest {
    private RadixSort radixSort = new RadixSort();
    @Test
    public void sort() {
        int[] nums = new int[]{12,22,11,8,29,29,20};
        nums = radixSort.sort(nums);
        for(int num:nums){
            System.out.println(num);
        }
    }
}