package io.github.sruby.arithmetic.binarysearch;

/**
 * @author sruby
 * @date 2020/8/6 10:27
 */
public class BinarySearch {
    public int search(int[] nums,int target){
        int low = 0;
        int high = nums.length-1;
        while (low <= high){
//            int mid = (high+low)/2;
            int mid = low+(high-low)>>1;
            if (target == nums[mid]){
                return mid;
            }
            if (target < nums[mid]){
                high = mid-1;
                continue;
            }
            if (target > nums[mid]){
                low = mid+1;
            }
        }
        return -1;
    }
}
