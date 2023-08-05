package io.github.sruby.arithmetic.array;

/**
 * 88 Merge Sorted Array
 *
 * @author Sruby
 * @date 2023/8/3 10:04
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0){
            System.arraycopy(nums2,0,nums1,0,n);
        }
        if (n == 0){
            return ;
        }

        int point1 = 1;
        int point2 = 1;
        int cur = 1;

        while (point2 <= n && point1 <=m){
            int item2 = nums2[n - point2];
            System.out.println(point1+"-"+point2+"-"+cur);
            int item1 = nums1[m - point1];
            if (item2 > item1){
                nums1[m+n-cur] = item2;
                point2 ++;
            }else {
                nums1[m+n-cur] = item1;
                point1 ++;
            }
            cur ++;
        }

        //考虑后一个指针还没有走完的情况，需要把剩下的元素填充到nums1中
        while (point2 <= n){
            int item2 = nums2[n - point2];
            nums1[m+n-cur] = item2;
            point2 ++;
            cur ++;
        }
    }
}
