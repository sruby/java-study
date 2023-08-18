package io.github.sruby.arithmetic.binarytree;

/**
 * test
 *
 * @author Sruby
 * @date 18/8/2023 16:05
 */
public class TraverseUtil {
    public static void traverse(TreeNode treeNode) {
        if (treeNode == null){
            return ;
        }
        System.out.println(treeNode.val);;
        traverse(treeNode.left);
        traverse(treeNode.right);
    }
}
