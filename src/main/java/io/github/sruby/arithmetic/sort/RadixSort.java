package io.github.sruby.arithmetic.sort;

/**
 * @date 2020/7/23 11:00
 */
public class RadixSort {
    public int[] sort(int[] nums){
        int max = nums[0];
        for(int i = 1;i<nums.length; i++){
            if (max < nums[i]){
                max = nums[i];
            }
        }

        int percent = 1;
        while (max/percent > 0 ){
            sortByPercent(nums,percent);
            percent *= 10;
        }

        return nums;
    }

    private void sortByPercent(int[] nums, int percent) {

        /**
         * count per number
         */
        int[] countArray = new int[10];
        for (int i = 0 ; i < nums.length; i++){
            int numOnCurrentPercent = nums[i] / percent % 10;
            countArray[numOnCurrentPercent]++;
        }

        /**
         *Calculate the position after sorting
         */
        for (int i =1; i < 10;i++){
            countArray[i] += countArray[i-1];
        }

        /**
         *store new array after sorting
         */
        int[] newArray = new int[nums.length];
        //must sort from tail
        for (int i = nums.length - 1; i >= 0; i--) {
             newArray[countArray[nums[i]/percent%10]-1] = nums[i];
             countArray[nums[i]/percent%10] --;
        }

        for (int i = 0 ;i < nums.length; i++){
            nums[i] = newArray[i];
        }
    }
}
