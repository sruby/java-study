package io.github.sruby.arithmetic.binarytree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * test
 *
 * @author Sruby
 * @date 8/27/2023 11:45 AM
 */
class SortAnArray_MergeSortTest {
    private SortAnArray_MergeSort sort = new SortAnArray_MergeSort();

    @Test
    void sortArray() {
        int[] nums = new int[]{5,1,1,2,0,0};
        int[] result = sort.sortArray(nums);

        Arrays.stream(result).forEach(System.out::println);
    }
}