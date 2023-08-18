package io.github.sruby.arithmetic.binarytree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 297 Serialize and Deserialize Binary Tree
 *
 * @author Sruby
 * @date 17/8/2023 17:17
 */
public class SerializeAndDeserializeBinaryTree_preorder {

    private static final String NULL_MARKER = "#";
    private static final String DELIMITER = ",";


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null){
            return NULL_MARKER;
        }

        return root.val+ DELIMITER + serialize(root.left) +DELIMITER+ serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0){
            return  null;
        }

        LinkedList<String> dataList = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));

        return deserialize(dataList);

    }

    private TreeNode deserialize(LinkedList<String> dataList) {
        if (dataList.isEmpty()){
            return null;
        }
//第一个是根节点
        String rootVal = dataList.removeFirst();
        if ( rootVal.equals(NULL_MARKER)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(rootVal));
//      先递归反序列化左子树，再反序列化右子树
        root.left = deserialize(dataList);
        root.right = deserialize(dataList);
        return root;
    }
}
