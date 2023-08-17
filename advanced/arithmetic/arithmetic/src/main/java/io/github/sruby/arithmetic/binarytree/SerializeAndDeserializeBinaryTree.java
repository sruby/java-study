package io.github.sruby.arithmetic.binarytree;

import java.util.LinkedList;

/**
 * 297 Serialize and Deserialize Binary Tree
 *
 * @author Sruby
 * @date 17/8/2023 17:17
 */
public class SerializeAndDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null){
            return "#";
        }

        return root.val + serialize(root.left) + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {


        if (data == null || data.length() == 0){
            return  null;
        }

        LinkedList<Character> dataList = new LinkedList<>();

        for (int i = 0 ;i< data.length(); i ++){
            dataList.add(data.charAt(i));
        }

        return deserialize(dataList);

    }

    private TreeNode deserialize(LinkedList<Character> dataList) {
        Character rootVal = dataList.removeFirst();
        if (dataList.isEmpty() || rootVal.equals("#")){
            return null;
        }

        TreeNode root = new TreeNode(rootVal);
        root.left = deserialize(dataList);
        root.right = deserialize(dataList);
        return null;
    }
}
