package io.github.sruby.arithmetic.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 297 Serialize and Deserialize Binary Tree
 *
 * @author Sruby
 * @date 17/8/2023 17:17
 */
public class SerializeAndDeserializeBinaryTree_levelorder {

    private static final String NULL_MARKER = "#";
    private static final String DELIMITER = ",";


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null){
            return null;
        }

        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);

        StringBuffer stringBuffer = new StringBuffer();

        while (!queue.isEmpty()){
            TreeNode item = queue.poll();

            stringBuffer.append(item).append(DELIMITER);

            TreeNode left = item.left;
            if (left == null){
                stringBuffer.append(NULL_MARKER).append(DELIMITER);
                continue;
            }

            TreeNode right = item.right;
            if (right == null){
                stringBuffer.append(NULL_MARKER).append(DELIMITER);
                continue;
            }


        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
    }

}
