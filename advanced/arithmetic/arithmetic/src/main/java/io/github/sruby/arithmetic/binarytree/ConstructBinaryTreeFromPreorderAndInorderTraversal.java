package io.github.sruby.arithmetic.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 105 Construct Binary Tree from Preorder and Inorder Traversal
 *
 * @author Sruby
 * @date 8/6/2023 4:06 PM
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /**
         * 构建哈希映射,便于定位根节点在中序遍历数组中的索引
         */
        indexMap = new HashMap<>();
        int length = inorder.length;
        for (int i = 0; i< length; i++){
            indexMap.put(inorder[i],i);
        }
//        ？length
        return build(preorder,inorder,0,length,0,length);
    }


    public TreeNode build(int[] preorder, int[] inorder, int preorderLeft, int preorderRight, int inorderLeft, int inorderRight){
        if (preorderLeft > preorderRight){
            return null;
        }

        int preorderRootIndex = preorderLeft;
        int inorderRootIndex = indexMap.get(preorder[preorderRootIndex]);

        //左子树元素个数
        int leftSubTreeSize = inorderRootIndex - inorderLeft;

        //构建根节点
        TreeNode root = new TreeNode(preorder[preorderRootIndex]);

//        递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = build(preorder,inorder,preorderLeft+1, preorderLeft + leftSubTreeSize + 1, inorderLeft, inorderRootIndex -1);

        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
//        左边界+左子树节点数目是先序遍历左子树的最后一个元素，+1之后就是右子树第一个元素
        root.right = build(preorder,inorder,preorderLeft+leftSubTreeSize+1, preorderRight, inorderRootIndex + 1, inorderRight);

        return root;
    }
}
