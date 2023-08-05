package io.github.sruby.arithmetic.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Sruby
 * @date 3/8/2023 16:32
 */
class TwoSum2InputArrayIsSortedTest {
    private TwoSum2InputArrayIsSorted twoSum2InputArrayIsSorted = new TwoSum2InputArrayIsSorted();

    @Test
    void twoSum() {
        int[] arr = {2,7,11,15};
        int[] twoSum = twoSum2InputArrayIsSorted.twoSum(arr, 9);
        assertArrayEquals(new int[]{1,2},twoSum);
    }

}