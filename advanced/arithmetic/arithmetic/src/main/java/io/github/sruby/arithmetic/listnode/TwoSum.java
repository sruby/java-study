package io.github.sruby.arithmetic.listnode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1。 Two Sum
 *
 * @author Sruby
 * @date 7/22/2023 4:50 PM
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0;  i< nums.length; i++){
            if (map.containsKey(target - nums[i])){
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
