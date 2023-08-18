package io.github.sruby.arithmetic.binarytree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 297 Serialize and Deserialize Binary Tree
 *
 * @author Sruby
 * @date 17/8/2023 17:17
 */
public class SerializeAndDeserializeBinaryTree_postorder {

    private static final String NULL_MARKER = "#";
    private static final String DELIMITER = ",";


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null){
            return NULL_MARKER;
        }

        return serialize(root.left) +DELIMITER+ serialize(root.right)+ DELIMITER + root.val ;
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
//最后一个元素是根节点
        String rootVal = dataList.removeLast();
        if ( rootVal.equals(NULL_MARKER)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(rootVal));
//       因为是从后往前遍历，反序列化，所以需要先反序列化右子树
        root.right = deserialize(dataList);
        root.left = deserialize(dataList);
        return root;
    }
}
