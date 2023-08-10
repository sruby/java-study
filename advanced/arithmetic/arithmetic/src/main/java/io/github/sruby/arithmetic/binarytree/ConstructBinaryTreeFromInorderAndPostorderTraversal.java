package io.github.sruby.arithmetic.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 106 Construct Binary Tree from Inorder and Postorder Traversal
 *
 * @author Sruby
 * @date 8/10/2023 10:09 PM
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    private Map<Integer,Integer> indexMap;


    public TreeNode buildTree(int[] inorder, int[] postorder) {
        /**
         * 构建哈希映射,便于定位根节点在中序遍历数组中的索引
         */
        indexMap = new HashMap<>();
        int length = inorder.length;
        for (int i = 0; i< length; i++){
            indexMap.put(inorder[i],i);
        }

        return build(inorder,postorder,0,inorder.length-1,0,postorder.length-1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int inLeft, int inRight, int postLeft, int postRight) {
        if (inLeft > inRight){
            return null;
        }

        int rootVal = postorder[postRight];
        Integer inRootIndex = indexMap.get(rootVal);
        int leftSubTreeSize = inRootIndex - inLeft + 1;

        TreeNode root = new TreeNode(rootVal);
        System.out.println(inLeft+","+(inRootIndex -1)+","+postLeft+","+(postLeft+leftSubTreeSize-1));
        root.left = build(inorder,postorder,inLeft,inRootIndex -1,postLeft,postLeft+leftSubTreeSize-1);
        System.out.println((inRootIndex+1)+","+inRight+","+(postLeft+leftSubTreeSize)+","+(postRight-1));
        root.right = build(inorder,postorder,inRootIndex+1,inRight,postLeft+leftSubTreeSize,postRight-1);

        return root;
    }

}
