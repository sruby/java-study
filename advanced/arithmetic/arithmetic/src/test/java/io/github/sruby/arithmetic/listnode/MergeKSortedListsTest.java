package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class MergeKSortedListsTest {

    @Test
    public void testMergeKLists() {
        // Create linked lists
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode list3 = new ListNode(2);
        list3.next = new ListNode(6);

        // Create an array of linked lists
        ListNode[] lists = {list1, list2, list3};

        // Expected merged list: 1->1->2->3->4->4->5->6
        ListNode expected = new ListNode(1);
        expected.next = new ListNode(1);
        expected.next.next = new ListNode(2);
        expected.next.next.next = new ListNode(3);
        expected.next.next.next.next = new ListNode(4);
        expected.next.next.next.next.next = new ListNode(4);
        expected.next.next.next.next.next.next = new ListNode(5);
        expected.next.next.next.next.next.next.next = new ListNode(6);

        // Create an instance of MergeKSortedLists
        MergeKSortedLists merger = new MergeKSortedLists();

        // Call the mergeKLists method and get the result
        ListNode result = merger.mergeKLists(lists);

        // Validate the result
        while (result != null && expected != null) {
            assertEquals(expected.val, result.val);
            expected = expected.next;
            result = result.next;
        }

        assertNull(result); // Ensure all elements have been merged
        assertNull(expected); // Ensure all elements have been covered
    }
}
