package io.github.sruby.arithmetic.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Sruby
 * @date 2/8/2023 11:25
 */
class RemoveDuplicatesFromSortedArrayTest {
    private RemoveDuplicatesFromSortedArray removeDuplicatesFromSortedArray = new RemoveDuplicatesFromSortedArray();

    @Test
    void removeDuplicates() {
        int[] nums = {1,1,2};
        int result = removeDuplicatesFromSortedArray.removeDuplicates(nums);
        assertEquals(2,result);
    }
}