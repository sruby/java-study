package io.github.sruby.arithmetic.listnode;

/**
 * Reverse Nodes in k-Group
 *
 * @author Sruby
 * @date 1/8/2023 16:55
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null){
            return null;
        }
        //b 指向的是每部分需要反转的链表的后继节点（也是下一部分需要反转的链表的头节点）
        ListNode a = head,b = head;


        for( int i = 0; i< k; i++){
            // 不足 k 个，不需要反转，base case
            if (b == null){
                return a;
            }
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a,b);
        System.out.println("newHead:"+newHead.val);
        //递归反转后的链表连接
        a.next = reverseKGroup(b,k);

        return newHead;
    }

    /**
     * 反转 [a，b}前闭后开
     * @param a
     * @param b
     * @return
     */
    public ListNode reverse(ListNode a, ListNode b){

        ListNode pre = new ListNode(-1);
        ListNode cur = a;
        while (cur != b){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
//            System.out.println("cur:"+cur.val+",b:"+b.val);
        }
        return pre;
    }
}
