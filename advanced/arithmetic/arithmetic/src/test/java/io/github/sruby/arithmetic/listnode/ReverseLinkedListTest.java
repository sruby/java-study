package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Test;

public class ReverseLinkedListTest {
    private ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
    @Test
    public void test() {
        // Test 1: An empty list
        ListNode head1 = null;
        assert reverseLinkedList.reverseList(head1) == null : "Test 1 failed";

        // Test 2: A list with one node
        ListNode head2 = new ListNode(1);
        assert reverseLinkedList.reverseList(head2) == head2 : "Test 2 failed";

    }


    @Test
    public void test2() {
        // Test 3: A list with multiple nodes
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        head3.next.next = new ListNode(3);
        ListNode reversedHead3 = reverseLinkedList.reverseList(head3);
        assert reversedHead3.val == 3 && reversedHead3.next.val == 2 && reversedHead3.next.next.val == 1 : "Test 3 failed";
    }
}

