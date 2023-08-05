package io.github.sruby.arithmetic.listnode;

/**
 *  83 Remove Duplicates from Sorted List
 *
 * @author Sruby
 * @date 2/8/2023 11:37
 */
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast !=null){
            if (slow.val != fast.val){
                slow = slow.next;
                slow.val = fast.val;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }
}
