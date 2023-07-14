package io.github.sruby.arithmetic.listnode;

/**
 * 19. Remove Nth Node From End of List
 * 遍历2遍的解法
 *
 * @author Sruby
 * @date 7/13/2023 5:41 PM
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null){
            return null;
        }

        /**
         * 统计链表长度
         */
        int length = 0;
        ListNode point = head;
        while (point!=null){
            length ++;
            point = point.next;
        }

        if (n > length){
            return head;
        }

        if (n == length){
            return head.next;
        }

        point = head;
        ListNode prePoint = head;
        int i = 0;
        while (point !=null){
            if (length-n == i){
                prePoint.next = point.next;
                point = null;
                break;
            }
            i++;
            prePoint = point;
            point = point.next;
        }

        return head;
    }
}
