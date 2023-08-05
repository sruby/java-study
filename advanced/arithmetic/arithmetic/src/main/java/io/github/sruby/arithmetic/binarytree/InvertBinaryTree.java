package io.github.sruby.arithmetic.binarytree;


/**
 * 226. Invert Binary Tree
 *
 *
 * @author Sruby
 * @date 4/8/2023 14:50
 */
public class InvertBinaryTree {

    /**
     * 遍历思路解决，交换左右节点
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        traverse(root);
        return root;
    }

    /**
     * 遍历并交换左右子树
     * @param root
     */
    public void traverse(TreeNode root){
        if (root == null){
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        traverse(root.left);
        traverse(root.right);
    }
}
