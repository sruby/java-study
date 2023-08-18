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

    @Test
    public void deserialize() {
        TreeNode root = serializeAndDeserializeBinaryTree.deserialize("1,2,#,#,3,4,#,#,5,#,#");

        TraverseUtil.traverse(root);
    }


    /**
     * Apologies for the confusion. You are correct, and I apologize for the incorrect information in my previous response.
     * In Java, when you use `Integer.valueOf()` with a character as the argument, it actually converts the character to its corresponding ASCII value. In ASCII, the character '1' has a decimal value of 49.
     * If you want to convert the character '1' to its corresponding integer value of 1, you can use the `Character.getNumericValue()` method instead. Here's the corrected code:
     * ```java
     * @Test
     * public void test() {
     *     Character a = '1';
     *     System.out.println(Character.getNumericValue(a));
     * }
     * ```
     * This code will correctly convert the character '1' to its integer value of 1 and print it to the console.
     */
    @Test
    public void test() {
        Character a ='1';
//        Character b = '-1';
        System.out.println(Integer.valueOf(a));
        System.out.println(Character.getNumericValue(a));
    }
}