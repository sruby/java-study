package io.github.sruby.arithmetic.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Sruby
 * @date 4/8/2023 15:03
 */
class InvertBinaryTreeTest {
    private InvertBinaryTree invertBinaryTree = new InvertBinaryTree();

    @Test
    void traverse() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        invertBinaryTree.traverse(root);

        assertEquals(3,root.left.val);
        assertEquals(1,root.right.val);
    }
}