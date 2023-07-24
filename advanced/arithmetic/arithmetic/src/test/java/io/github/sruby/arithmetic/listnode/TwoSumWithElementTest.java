package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Sruby
 * @date 7/22/2023 5:00 PM
 */
class TwoSumWithElementTest {
    private TwoSumWithElement twoSum = new TwoSumWithElement();

    @Test
    void twoSum() {
        int[] result = twoSum.twoSum(new int[]{2,7,11,15}, 9);
        assertEquals(result.length,2);
        assertEquals(result[0],2);
        assertEquals(result[1],7);
    }
}