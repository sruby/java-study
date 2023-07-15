package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListCycleIITest {

    @Test
    public void testDetectCycle() {
        LinkedListCycleII detector = new LinkedListCycleII();

        // Test case 1: Empty list
        assertNull(detector.detectCycle(null));

        // Test case 2: List with no cycle
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        assertNull(detector.detectCycle(head));

        // Test case 2。1: List with no cycle
        head = new ListNode(1);
        assertNull(detector.detectCycle(head));

        // Test case 3: List with cycle
        head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // create cycle
        assertEquals(node2, detector.detectCycle(head));
    }


    @Test
    public void testDetectCycle2() {
        LinkedListCycleII detector = new LinkedListCycleII();

        // Test case 2。1: List with no cycle
        ListNode head = new ListNode(1);
        assertNull(detector.detectCycle(head));
    }
}
