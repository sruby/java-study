package io.github.sruby.arithmetic.sort;

import java.util.Arrays;

/**
 * 912. Sort an Array
 * @date 2020/7/8 9:40
 */
public class BubbleSort {
    /**
     * sort
     * @param nums
     */
    public int[] sort(int[] nums){
        if (1 > nums.length || nums.length > 50000){
            return null;
        }

        boolean isChange = false;
        for (int i=0;i<nums.length;i++){
            if(-50000 > nums[i] || nums[i] > 50000){
                return null;
            }
            int temp;
            for (int j=0;j<nums.length-i-1;j++){
                if (nums[j] > nums[j+1]){
                    temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                    isChange = true;
                }
            }
            if (!isChange){
                break;
            }
        }

        return nums;
    }

    /**
     * sort time limit exceeded
     * @param nums
     */
    public int[] sort2(int[] nums){
        if (1 > nums.length || nums.length > 50000){
            return null;
        }

//        boolean isChange = false;
        for (int i=0;i<nums.length;i++){
            if(-50000 > nums[i] || nums[i] > 50000){
                return null;
            }
            int temp;
            for (int j=i+1;j<nums.length;j++){
                if (nums[i] > nums[j]){
                    temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
//                    isChange = true;
                }
            }
//            if (!isChange){
//                break;
//            }
            Arrays.stream(nums).forEach(System.out::println);
            System.out.println("---------------");
        }

        return nums;
    }


}
