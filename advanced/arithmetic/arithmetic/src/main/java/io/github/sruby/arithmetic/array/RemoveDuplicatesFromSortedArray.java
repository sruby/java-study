package io.github.sruby.arithmetic.array;

/**
 * 26. Remove Duplicates from Sorted Array
 *
 * @author Sruby
 * @date 2/8/2023 11:13
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums == null){
            return -1;
        }
        if (nums.length <= 1){
            return 1;
        }

        int slow =0, fast = 1;

        while (slow <= fast && fast < nums.length){
            if (nums[slow] != nums[fast]){
//                慢指针+1再填充
                slow ++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow+1;
    }
}
