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

        ListNode leftNode = head;

        /**
         * 移动left-2，找到 leftNode节点
         */
        for (int i = 0; i < left -2 ; i++){
            leftNode = leftNode.next;
        }

        /**
         * nextLeftNode
         */
        ListNode nextLeftNode;
        if (left == 1){
            nextLeftNode = leftNode;
        }else {
            nextLeftNode = leftNode.next;
            //断开left链接
            leftNode.next = null;
        }

        /**
         * 移动right-left,找到rightNode
         */
        ListNode rightNode = nextLeftNode;
        //head = [1]
//        if (rightNode == null){
//            return head;
//        }
        for (int i = 0; i < right - left; i++){
            rightNode = rightNode.next;
        }

        ListNode nextRightNode = rightNode.next;

        //断开right链接
        rightNode.next = null;

        /**
         * 反转中间部分
         */
        ListNode reversedList = reverseList(nextLeftNode);

        if (left != 1){
            leftNode.next = reversedList;
            nextLeftNode.next = nextRightNode;
            return head;
        }else {
            nextLeftNode.next = nextRightNode;
            return reversedList;
        }
    }

    /**
     * 反转链表
     * @param newHead
     * @return
     */
    private ListNode reverseList(ListNode newHead) {
        ListNode cur = newHead;
        ListNode pre = null;
        while (cur !=null){
            ListNode temp = cur.next;
            cur.next = pre;

            pre = cur;
            cur = temp;
        }
//        return pre 而不是cur,cur最后会是null
        return pre;
    }
}
