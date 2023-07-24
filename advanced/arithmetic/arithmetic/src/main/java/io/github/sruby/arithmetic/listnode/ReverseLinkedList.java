package io.github.sruby.arithmetic.listnode;

/**
 * 206. Reverse Linked List
 *
 * @author Sruby
 * @date 19/7/2023 14:53
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode last = null;
        if (head.next == null){
            last = head;
            return head;
        }

        head = head.next;
        ListNode reverseList = reverseList(head);
        reverseList.next = head;

        return last;
    }
}
