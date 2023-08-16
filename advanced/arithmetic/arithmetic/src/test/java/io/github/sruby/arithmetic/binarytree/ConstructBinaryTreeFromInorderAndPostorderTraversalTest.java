package io.github.sruby.arithmetic.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author Sruby
 * @date 8/10/2023 10:32 PM
 */
class ConstructBinaryTreeFromInorderAndPostorderTraversalTest {
    private ConstructBinaryTreeFromInorderAndPostorderTraversal constructBinaryTreeFromInorderAndPostorderTraversal = new ConstructBinaryTreeFromInorderAndPostorderTraversal();

    @Test
    void buildTree() {
        TreeNode treeNode = constructBinaryTreeFromInorderAndPostorderTraversal.buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});

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