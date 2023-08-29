package io.github.sruby.arithmetic.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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

            if (item == null){
                stringBuffer.append(NULL_MARKER).append(DELIMITER);
                continue;
            }

            stringBuffer.append(item.val).append(DELIMITER);

            TreeNode left = item.left;
            TreeNode right = item.right;
            queue.offer(left);
            queue.offer(right);
        }
        return stringBuffer.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> list = Arrays.asList(data.split(DELIMITER));

        String rootVal = list.get(0);
        TreeNode root = new TreeNode(Integer.valueOf(rootVal));

        Queue<TreeNode> queue = new LinkedList<>();

        int index = 1;

        while (!queue.isEmpty()){
            for (int i = 0; i< queue.size(); i ++){
                TreeNode item = queue.poll();
                String leftVal = list.get(index);
                index ++;
                if (!NULL_MARKER.equals(leftVal)){
                    TreeNode left = new TreeNode(Integer.parseInt(leftVal));
                    item.left = left;
                    queue.offer(left);
                }

                String rightVal = list.get(index);
                index ++;
                if (!NULL_MARKER.equals(rightVal)){
                    TreeNode right = new TreeNode(Integer.parseInt(rightVal));
                    item.right = right;
                    queue.offer(right);
                }

            }
        }
        return root;
    }

}
