package io.github.sruby.arithmetic.listnode;

/**
 * 142. Linked List Cycle II
 *
 * @author Sruby
 * @date 7/15/2023 11:54 AM
 */
public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null){
            return null;
        }

        /**
         * 寻找相遇点
         */
        ListNode slow = head;
        ListNode fast = head;

        while (fast!=null && fast.next !=null ){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                break;
            }
        }

        /**
         * 解决head=[1]以及其他没有环的场景
         */
        if (fast == null || fast.next == null){
            return null;
        }

        /**
         * slow从head出发，fast从相遇点出发，再次相遇则是起点
         */
        slow = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return  slow;
    }
}
