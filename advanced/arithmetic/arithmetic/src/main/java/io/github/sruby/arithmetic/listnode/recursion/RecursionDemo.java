package io.github.sruby.arithmetic.listnode.recursion;

/**
 * recursion
 *
 * @author Sruby
 * @date 7/29/2023 4:21 PM
 */
public class RecursionDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        printArr(arr,arr.length-1);

        int result = search(arr, 3);
        System.out.println(result);
    }

    /**
     * 打印数组中的数值
     * @param arr
     * @param index
     */
    public static void printArr(int[] arr, int index){
        if (index < 0){
            return;
        }

        System.out.println(arr[index]);
        printArr(arr,--index);
    }

    public static int search(int[] arr, int targetVal){
        return search(arr,targetVal,arr.length-1);
    }
    public static int search(int[] arr, int targetVal,int index){
        if (index < 0){
            return -1;
        }

        if (arr[index] == targetVal){
            return index;
        }

        return search(arr,targetVal,--index);
    }
}
