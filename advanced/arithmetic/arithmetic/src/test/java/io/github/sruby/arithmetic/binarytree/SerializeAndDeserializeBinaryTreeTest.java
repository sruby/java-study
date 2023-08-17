package io.github.sruby.arithmetic.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Sruby
 * @date 17/8/2023 17:25
 */
class SerializeAndDeserializeBinaryTreeTest {
    private SerializeAndDeserializeBinaryTree serializeAndDeserializeBinaryTree = new SerializeAndDeserializeBinaryTree();

    @Test
    void serialize() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String serialize = serializeAndDeserializeBinaryTree.serialize(root);
        System.out.println(serialize);
    }
}