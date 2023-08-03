package io.github.sruby.arithmetic.array;

/**
 * 5. Longest Palindromic Substring
 *
 * @author Sruby
 * @date 3/8/2023 16:46
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String result = "";
        for (int i=0;i<s.length();i++){
            String result1 = palindromic(s, i, i);
            String result2 = palindromic(s, i, i + 1);
            result = result1.length() > result.length() ? result1:result;
            result = result2.length() > result.length() ? result2:result;
        }
        return  result;
    }

    /**
     * 查找以 left 和 right 为中心的最长回文子串
     * @param s
     * @param left
     * @param right
     */
    private String palindromic(String s,int left,int right){
        char[] charArray = s.toCharArray();
        while (left >= 0 && right < s.length() && charArray[left] == charArray[right]){
            left--;
            right++;
        }
        return s.substring(left+1,right);
    }
}
