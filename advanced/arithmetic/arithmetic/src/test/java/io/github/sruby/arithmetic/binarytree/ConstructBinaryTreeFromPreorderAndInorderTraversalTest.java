package io.github.sruby.arithmetic.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Sruby
 * @date 8/10/2023 9:09 PM
 */
class ConstructBinaryTreeFromPreorderAndInorderTraversalTest {
    private ConstructBinaryTreeFromPreorderAndInorderTraversal constructBinaryTreeFromPreorderAndInorderTraversal = new ConstructBinaryTreeFromPreorderAndInorderTraversal();

    @Test
    void buildTree() {
        TreeNode treeNode = constructBinaryTreeFromPreorderAndInorderTraversal.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});

        traverse(treeNode);
    }

    private void traverse(TreeNode treeNode) {
        if (treeNode == null){
            return ;
        }
        System.out.println(treeNode.val);;
        traverse(treeNode.left);
        traverse(treeNode.right);
    }
}