package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Test;

/**
 * test
 *
 * @author Sruby
 * @date 7/8/2023 10:12 AM
 */
class MergeTwoSortedListsTest {

    private MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();

    @Test
    void mergeTwoLists() {

        ListNode list1 = new ListNode(1);
        list1.next= new ListNode(3);
        list1.next.next= new ListNode(8);
        list1.next.next.next = new ListNode(10);
        list1.next.next.next.next= new ListNode(100);

        ListNode list2 = new ListNode(1);
        list2.next= new ListNode(2);
        list2.next.next= new ListNode(7);
        list2.next.next.next = new ListNode(11);
        list2.next.next.next.next= new ListNode(90);
        ListNode listNode = mergeTwoSortedLists.mergeTwoLists(list1, list2);

        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }
}