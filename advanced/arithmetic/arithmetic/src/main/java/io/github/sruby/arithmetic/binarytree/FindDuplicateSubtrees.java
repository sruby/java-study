package io.github.sruby.arithmetic.binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 652 Find Duplicate Subtrees
 *
 * @author Sruby
 * @date 8/12/2023 6:35 PM
 */
public class FindDuplicateSubtrees {
    //子树串为key，出现次数为value,记录所有子树以及出现的次数
    private Map<String,Integer> countMap = new HashMap();
//    记录重复的子树根节点
    private List<TreeNode> result = new LinkedList();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        serialize(root);
        return result;
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    private String serialize(TreeNode root) {
//        用非数字的特殊符#表示空指针，并且用,分隔每个二叉树节点值，这属于序列化二叉树的套路了
        if (root == null){
            return "";
        }

        String subLeftTreeStr = serialize(root.left);
        String subRightTreeStr = serialize(root.right);

        String subTreeStr = subLeftTreeStr +"," +subRightTreeStr +"," + root.val;
        Integer subTreeCount = countMap.getOrDefault(subTreeStr, 0);
        if (subTreeCount == 1){
            result.add(root);
        }
        countMap.put(subTreeStr,subTreeCount + 1);
        return subTreeStr;
    }
}
