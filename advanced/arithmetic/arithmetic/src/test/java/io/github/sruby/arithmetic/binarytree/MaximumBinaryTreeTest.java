package io.github.sruby.arithmetic.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Sruby
 * @date 8/6/2023 12:05 AM
 */
class MaximumBinaryTreeTest {

    private MaximumBinaryTree maximumBinaryTree = new MaximumBinaryTree();
    @Test
    void constructMaximumBinaryTree() {
        int[] nums = {3,2,1,6,0,5};
        TreeNode treeNode = maximumBinaryTree.constructMaximumBinaryTree(nums);
        assertEquals(6,treeNode.val);
        assertEquals(3,treeNode.left.val);
        assertEquals(5,treeNode.right.val);
    }
}