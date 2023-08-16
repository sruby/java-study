package io.github.sruby.arithmetic.binarytree;

/**
 * 116 Populating Next Right Pointers in Each Node
 *
 * @author Sruby
 * @date 4/8/2023 15:08
 */
public class PopulatingNextRightPointersInEachNode {
    public Node connect(Node root) {
        if (root == null){
            return null;
        }
        root.next = null;
        traverse(root);
        return root;
    }

    /**
     * 遍历连接 next
     * @param root
     */
    public void traverse(Node root){
        if (root == null){
            return ;
        }

        if (root.left == null || root.right == null){
            return;
        }

        root.left.next = root.right;

        if (root.next == null){
            root.right.next = null;
        }else {
            root.right.next = root.next.left;
        }

        traverse(root.left);
        traverse(root.right);
    }
}
