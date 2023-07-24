package io.github.sruby.arithmetic.listnode;

/**
 * 2. Add Two Numbers
 *
 * @author Sruby
 * @date 7/22/2023 5:31 PM
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode newHead = new ListNode(-1);
        ListNode newPoint = newHead;
        int carry = 0;

        while (p1 !=null || p2 !=null || carry > 0){
            int val = carry;

            if (p1 !=null){
                val = p1.val + val;
                p1 = p1.next;
            }

            if (p2 !=null){
                val = p2.val + val;
                p2 = p2.next;
            }

            //超过10的数值处理
            carry = val / 10;
            val = val % 10;

            ListNode newNode = new ListNode(val);
            newPoint.next = newNode;
            newPoint = newPoint.next;

        }

        return newHead.next;
    }
}
