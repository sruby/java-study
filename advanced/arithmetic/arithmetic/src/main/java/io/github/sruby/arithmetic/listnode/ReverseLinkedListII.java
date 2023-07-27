package io.github.sruby.arithmetic.listnode;

/**
 * 92. Reverse Linked List II
 *
 * @author Sruby
 * @date 7/15/2023 6:06 PM
 */
public class ReverseLinkedListII {

    /**
     * reverse between
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        /**
         * 移动 left-1，找到 pre 前驱节点
         * 移动 right,找到successor 后继节点
         */
        ListNode dump = new ListNode(-1);
        dump.next = head;
        ListNode pre = head;
        ListNode successor = null;

        for (int i = 0; i < right ; i++){
            if (i < left -2){
                pre = pre.next;
            }else if(i == left - 2){
                successor = pre;
            }else {
                successor = successor.next;
            }
        }

        /**
         * 反转中间部分
         */
        ListNode newHead = pre.next;
        reverseList(newHead);

        return head;
    }

    private ListNode reverseList(ListNode newHead) {
        ListNode p = newHead;
        while (p.next !=null){
            ListNode temp = p.next;
            p.next.next = p;
            p.next = null;
            p = temp;
        }
        return p.next;
    }
}
