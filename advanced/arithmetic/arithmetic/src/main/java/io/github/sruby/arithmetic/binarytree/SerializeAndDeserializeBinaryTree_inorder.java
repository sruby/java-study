package io.github.sruby.arithmetic.binarytree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 297 Serialize and Deserialize Binary Tree
 *
 * @author Sruby
 * @date 17/8/2023 17:17
 */
public class SerializeAndDeserializeBinaryTree_inorder {

    private static final String NULL_MARKER = "#";
    private static final String DELIMITER = ",";


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null){
            return NULL_MARKER;
        }

        return serialize(root.left)+ DELIMITER + root.val  +DELIMITER+ serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0){
            return  null;
        }

        LinkedList<String> dataList = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));

        return deserialize(dataList);

    }

    //中序遍历无法实现反序列化
    private TreeNode deserialize(LinkedList<String> dataList) {
     return null;
    }
}
