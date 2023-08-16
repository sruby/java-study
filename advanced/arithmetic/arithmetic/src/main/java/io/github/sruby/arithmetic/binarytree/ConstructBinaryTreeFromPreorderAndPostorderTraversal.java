package io.github.sruby.arithmetic.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 889 Construct Binary Tree from Preorder and Postorder Traversal
 *
 * @author Sruby
 * @date 8/12/2023 5:26 PM
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    private Map<Integer,Integer> indexMap;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        indexMap = new HashMap<>();
        int length = postorder.length;
        for (int i = 0; i < length; i ++){
            indexMap.put(postorder[i],i);
        }

        return bulid(preorder,postorder,0,length-1,0,length-1);
    }

    private TreeNode bulid(int[] preorder, int[] postorder, int preLeft, int preRight, int postLeft, int postRight) {
        System.out.println(preLeft+","+preRight+","+postLeft+","+postRight);
        if (preLeft >  preRight){
            return null;
        }

        // 因为构造左子树的时候(root.left)，可能存在left == right的情况
        // 导致无限递归下去最终使得数组越界
//        TODO
        if (preLeft == preRight){
            return  new TreeNode(preorder[preLeft]);
        }

        int preSubTreeRootIndex = preLeft + 1;
        int subLeftRoot = preorder[preSubTreeRootIndex];
        Integer subLeftRootIndexInPost = indexMap.get(subLeftRoot);
        int leftSubTresSize = subLeftRootIndexInPost - postLeft + 1;

        TreeNode root = new TreeNode(preorder[preLeft]);
        root.left = bulid(preorder,postorder,preSubTreeRootIndex,preSubTreeRootIndex+leftSubTresSize-1,postLeft,postLeft+leftSubTresSize-1);
        root.right = bulid(preorder,postorder,preLeft+leftSubTresSize+1,preRight,postLeft+leftSubTresSize,postRight-1);

        return root;
    }
}
