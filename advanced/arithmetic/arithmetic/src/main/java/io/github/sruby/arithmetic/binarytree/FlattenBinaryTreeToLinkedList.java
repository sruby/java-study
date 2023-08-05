package io.github.sruby.arithmetic.binarytree;

/**
 * 114 flatten Binary Tree to Linked List
 *
 * @author Sruby
 * @date 4/8/2023 16:59
 */
public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        if (root == null){
            return ;
        }

        flatten(root.left);
        flatten(root.right);

        TreeNode oldLeft = root.left;
        TreeNode oldRight = root.right;

        /**
         * 左节点变成又节点，左节点设置为null
         */
        root.right = oldLeft;
        root.left = null;

        /**
         * 原有右节点接到新的右节点末尾
         *
         */
        TreeNode point = root.right;
        while (point.right !=null){ //point.right !=null 而不是 point !=null,叶子节点的right是null，会导致空指针
            point = point.right;
        }
        point.right = oldRight;

    }
}
