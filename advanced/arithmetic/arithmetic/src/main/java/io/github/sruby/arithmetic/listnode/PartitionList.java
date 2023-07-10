package io.github.sruby.arithmetic.listnode;

/**
 * 86. Partition List
 *
 * @author Sruby
 * @date 10/7/2023 18:14
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if (head == null){
            return null;
        }

        ListNode firstDummy = new ListNode(-1);
        ListNode lastDummy = new ListNode(-1);
        ListNode firstPoint = firstDummy;
        ListNode lastPoint = lastDummy;

        while (head != null){
            if (head.val < x){
                firstPoint.next = head;
                firstPoint = firstPoint.next;
            }else {
                lastPoint.next = head;
                lastPoint = lastPoint.next;
            }
            head = head.next;
        }
        if (lastDummy.next !=null){
            lastPoint.next = firstDummy.next;
        }

        return firstDummy.next;
    }
}
