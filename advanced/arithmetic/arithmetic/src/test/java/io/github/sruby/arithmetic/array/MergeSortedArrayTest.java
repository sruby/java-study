package io.github.sruby.arithmetic.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * TODO
 *
 * @author Sruby
 * @date 2023/8/3 10:36
 */
class MergeSortedArrayTest {
    private MergeSortedArray mergeSortedArray = new MergeSortedArray();

    @Test
    void merge() {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        mergeSortedArray.merge(nums1,3, nums2, 3);
        Arrays.stream(nums1).forEach(System.out::println);
    }

    @Test
    public void test() {
        int[] nums1 = {2,0};
        int[] nums2 = {1};
        mergeSortedArray.merge(nums1,1, nums2, 1);
        Arrays.stream(nums1).forEach(System.out::println);
    }
}