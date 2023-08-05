package io.github.sruby.arithmetic.binarytree;

/**
 * 654 Maximum Binary Tree
 *
 * @author Sruby
 * @date 8/5/2023 11:38 PM
 */
public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums,0,nums.length-1);
    }

    private TreeNode build(int[] nums, int low, int hight) {
        if (low > hight){
            return null;
        }

        /**
         * 找到最大元素作为root
         */
        int index =low;
        int maxValue = Integer.MIN_VALUE;
        for (int i=low; i <= hight; i++){
            if (nums[i] > maxValue){
                maxValue = nums[i];
                index = i;
            }
        }

        /**
         * 递归构造左右子树
         */
//        根节点
        TreeNode root = new TreeNode(maxValue);
//        分别要在index基础上-1作为左子树的最大索引，+1作为右子树的最小索引
        root.left = build(nums,low,index-1);
        root.right = build(nums,index+1,hight);

        return root;
    }
}
