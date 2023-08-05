package io.github.sruby.arithmetic.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Sruby
 * @date 3/8/2023 16:55
 */
class LongestPalindromicSubstringTest {
    private LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();

    @Test
    void longestPalindrome() {
        String result = longestPalindromicSubstring.longestPalindrome("babad");
        assertEquals("bab",result);
    }
}