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
//        printArr(arr,0);
        reversEprintArr(arr,0);
//
//        int result = search(arr, 3);
//        System.out.println(result);
    }

    /**
     * 顺序打印数组中的数值（类似二叉树的前序遍历，在递归之前打印）
     * @param arr
     * @param index
     */
    public static void printArr(int[] arr, int index){
        if (index >= arr.length){
            return;
        }

        System.out.println(arr[index]);
        printArr(arr,++index);
    }
    /**
     * 倒序打印数组中的数值（类似二叉树的后序遍历，在递归之后打印）
     * @param arr
     * @param index
     */
    public static void reversEprintArr(int[] arr, int index){
        if (index >= arr.length){
            return;
        }
        // 传递 index + 1，保持索引不变
//        reversEprintArr(arr,++index);
        reversEprintArr(arr,index+1);

        System.out.println(arr[index]);
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
