package io.github.sruby.arithmetic.listnode;

import java.util.Arrays;

/**
 * Two Sum 返回元素而不是索引
 *
 * @author Sruby
 * @date 7/22/2023 4:50 PM
 */
public class TwoSumWithElement {
    public int[] twoSum(int[] nums, int target) {
        //导致乱序，
        Arrays.sort(nums);

        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while ( left < length && right > 0){
            int sum = nums[left] + nums[right];
            if (sum > target){
                right --;
            }else if (sum < target){
                left ++;
            }else {
                return new int[]{nums[left],nums[right]};
            }
        }
        return new int[]{nums[left],nums[right]};
    }
}
