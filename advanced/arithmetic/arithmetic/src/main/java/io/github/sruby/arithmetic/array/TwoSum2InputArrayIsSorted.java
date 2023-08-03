package io.github.sruby.arithmetic.array;

/**
 * 167. Two Sum II - Input Array Is Sorted
 *
 * @author Sruby
 * @date 3/8/2023 16:27
 */
public class TwoSum2InputArrayIsSorted {
    public int[] twoSum(int[] numbers, int target) {
        int left =0;
        int right = numbers.length-1;
        while (left <=right){
            int leftValue = numbers[left];
            int rightValue = numbers[right];
            int sum = leftValue + rightValue;
            if (sum > target){
                right --;
            }else if (sum < target){
                left ++;
            }else {
                return new int[]{left+1,right+1};
            }
        }
        return null;
    }
}
