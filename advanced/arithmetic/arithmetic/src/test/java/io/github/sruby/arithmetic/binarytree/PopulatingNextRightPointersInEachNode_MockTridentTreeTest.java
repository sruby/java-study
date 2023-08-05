package io.github.sruby.arithmetic.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author Sruby
 * @date 4/8/2023 16:50
 */
class PopulatingNextRightPointersInEachNode_MockTridentTreeTest {
    private PopulatingNextRightPointersInEachNode_MockTridentTree service = new PopulatingNextRightPointersInEachNode_MockTridentTree();

    @Test
    void connect() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        service.connect(root);

        assertEquals(3, root.left.next.val);
        assertNull(root.right.next);
    }
}