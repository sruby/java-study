package io.github.sruby.arithmetic.listnode;

/**
 * 206. Reverse Linked List
 *
 * @author Sruby
 * @date 7/15/2023 6:13 PM
 */
public class ReverseLinkedListWithTwoPoints {
    /**
     * 以链表1->2->3->4->5举例
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null){
            return null;
        }

        ListNode pre = null;
        ListNode cur = head;

        while (cur !=null){
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return  pre;
    }
}
