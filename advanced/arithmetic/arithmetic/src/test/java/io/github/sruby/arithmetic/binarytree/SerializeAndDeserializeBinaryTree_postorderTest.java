package io.github.sruby.arithmetic.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author Sruby
 * @date 18/8/2023 16:52
 */
class SerializeAndDeserializeBinaryTree_postorderTest {
    private SerializeAndDeserializeBinaryTree_postorder serializeAndDeserializeBinaryTreePostorder = new SerializeAndDeserializeBinaryTree_postorder();

    @Test
    void serialize() {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String serialize = serializeAndDeserializeBinaryTreePostorder.serialize(root);
        System.out.println(serialize);
    }

    @Test
    void deserialize() {
        TreeNode root = serializeAndDeserializeBinaryTreePostorder.deserialize("#,#,2,#,#,4,#,#,5,3,1");

        TraverseUtil.traverse(root);
    }
}