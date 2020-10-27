package io.github.sruby.arithmetic.binarysearch;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @date 2020/8/6 10:34
 */
public class BinarySearchTest {
    private BinarySearch binarySearch = new BinarySearch();

    @Test
    public void search() {
        int[] nums = new int[]{1,2,2,2,2,2,3,4,10,12,13,18,19,31,41,42,43,100,101,200,340,2300,4000,20000,30000};
        int search = binarySearch.search(nums, 10);
        System.out.println(search);
    }
    @Test
    public void search2() {
        int[] nums = new int[]{-1,0,3,5,9,12};
        int search = binarySearch.search(nums, 9);
        System.out.println(search);
    }
}