package io.github.sruby.arithmetic.binarytree;

/**
 * 116 Populating Next Right Pointers in Each Node
 *
 * @author Sruby
 * @date 4/8/2023 15:08
 */
public class PopulatingNextRightPointersInEachNode_MockTridentTree {
    public Node connect(Node root) {
        if (root == null){
            return null;
        }

        traverse(root.left,root.right);

        return root;
    }

    /**
     * 模拟三叉树，处理连接问题
     */
    public void traverse(Node node1, Node node2){
        if (node1 == null || node2 == null){
            return;
        }

        node1.next = node2;

        traverse(node1.left,node1.right);
        traverse(node2.left,node2.right);
        traverse(node1.right,node2.left);
    }
}
