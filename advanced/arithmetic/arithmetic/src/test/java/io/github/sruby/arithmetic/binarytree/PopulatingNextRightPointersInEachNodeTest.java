package io.github.sruby.arithmetic.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Sruby
 * @date 4/8/2023 15:38
 */
class PopulatingNextRightPointersInEachNodeTest {
    private PopulatingNextRightPointersInEachNode populatingNextRightPointersInEachNode = new PopulatingNextRightPointersInEachNode();

    @Test
    void connect() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        populatingNextRightPointersInEachNode.connect(root);

        assertEquals(3, root.left.next.val);
        assertNull(root.right.next);
    }
}