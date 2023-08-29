package io.github.sruby.arithmetic.binarytree;

/**
 * 912 Sort an Array
 *
 * @author Sruby
 * @date 8/27/2023 11:05 AM
 */
public class SortAnArray_MergeSort2 {
//    用于辅助合并有序数组
    private static  int[] temp;

    public int[] sortArray(int[] nums) {
        // 先给辅助数组开辟内存空间
        temp = new int[nums.length];
        // 排序整个数组（原地修改）
        sort(nums, 0,nums.length -1);
        return  nums;
    }

    /**
     * sort
     * @param nums
     * @param low
     * @param high
     */
    private void sort(int[] nums, int low, int high) {
        if (low == high){
            return ;
        }
        // 这样写是为了防止溢出，效果等同于 (hi + lo) / 2
        int middle = low + (high - low) /2;
        // 先对左半部分数组 nums[lo..mid] 排序
        sort(nums,low,middle);
        // 再对右半部分数组 nums[mid+1..hi] 排序
        sort(nums,middle+1, high);
// 将两部分有序数组合并成一个有序数组
        merge(nums,low,middle,high);
    }

    /**
     * merge
     * @param nums
     * @param low
     * @param middle
     * @param high
     */
    private void merge(int[] nums, int low, int middle, int high) {
//        System.out.println("low:"+low+",middle:"+middle+",high:"+high);
        // 先把 nums[lo..hi] 复制到辅助数组中,不能使用copy的方式，需要保持数组的索引位置不变
//        temp = Arrays.copyOfRange(nums,low,high+1);

        // 以便合并后的结果能够直接存入 nums
        for (int i = low; i <= high; i++) {
            temp[i] = nums[i];
        }

//        Arrays.stream(temp).forEach(System.out::println);
//        System.out.println("------------------");

        // 数组双指针技巧，合并两个有序数组，i指向左边的子数组的初始索引，j指向右边子数组的初始索引
        int i = low; int j = middle +1;
        for (int p = low; p <= high; p++){
//            System.out.println("i:"+i+",j:"+j+",p:"+p);
            if (i == middle +1){
//                左边数组已全部合并；
                nums[p] = temp[j++];
            }else if (j == high + 1){
//                右边数组已全部合并
                nums[p] = temp[i++];
            }else  if (temp[i] > temp[j]){
                nums[p] = temp[j++];
            }else {
                nums[p] = temp[i++];
            }
        }
    }
}
