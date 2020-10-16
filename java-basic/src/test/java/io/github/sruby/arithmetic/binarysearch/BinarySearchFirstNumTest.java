package io.github.sruby.arithmetic.binarysearch;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @date 2020/8/12 15:43
 */
public class BinarySearchFirstNumTest {
    private BinarySearchFirstNum binarySearchFirstNum = new BinarySearchFirstNum();
    @Test
    public void search() {
        int nums[] = new int[]{0,1,1,2,3};
        int search = binarySearchFirstNum.search(nums, 1);
        System.out.println(search);
    }
}