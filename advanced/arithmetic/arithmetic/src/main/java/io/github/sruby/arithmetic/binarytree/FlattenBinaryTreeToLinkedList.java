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

        if (root.left == null || root.right == null){
            return ;
        }

        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        root.right.right = temp;
        root.right.left = null;

        flatten(root.left);
        flatten(root.right);
    }
}
