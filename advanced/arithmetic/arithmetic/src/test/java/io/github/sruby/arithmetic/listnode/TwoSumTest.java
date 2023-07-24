package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * test
 *
 * @author Sruby
 * @date 7/22/2023 5:00 PM
 */
class TwoSumTest {
    private TwoSum twoSum = new TwoSum();

    @Test
    void twoSum() {
        // Test case 1: Normal case
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] expected1 = {1, 0};
        assertArrayEquals(expected1, twoSum.twoSum(nums1, target1));
    }
}