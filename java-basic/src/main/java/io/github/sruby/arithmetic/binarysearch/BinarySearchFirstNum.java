package io.github.sruby.arithmetic.binarysearch;

/**
 *
 * @date 2020/8/11 11:06
 */
public class BinarySearchFirstNum {

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int nums[],int target){
        int low = 0;
        int high = nums.length - 1;


        while (low <= high){
            int middle = low + ((high - low)>>1);
            if (target < nums[middle]){
                high = middle -1;
            }else if (target > nums[middle]){
                low = middle + 1;
            }else {
                if (middle == 0 || nums[middle] != nums[middle-1]){
                    return middle;
                }else if (nums[middle] == nums[middle-1]){
                    high = middle -1;
                }else {
                    break;
                }
            }
        }

        return -1;
    }
}
