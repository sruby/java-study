package io.github.sruby.arithmetic.sort;

/**
 * @date 2020/7/14 15:02
 */
public class QuickSort {

    public int[] sortArray(int[] nums){

        sort(nums,0 ,nums.length-1);

        return nums;
    }

    public void sort(int[] nums,int start,int end){
        if (start >= end){
            return;
        }

        int pivot = partition(nums,start,end);
        sort(nums,start,pivot-1);
        sort(nums,pivot+1,end);
    }


    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int left = start;
        for (int right=start;right<end;right++){

            if (nums[right] < pivot){
                if (left != right){
                    swap(nums,left,right);
                }
                left++;
            }
        }
        swap(nums,left,end);
        return left;
    }


    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


}
