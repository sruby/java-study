package io.github.sruby.arithmetic.listnode;

/**
 * 23. Merge k Sorted Lists
 *
 * @author Sruby
 * @date 11/7/2023 15:46
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null){
            return null;
        }

        if (lists.length == 0)
        {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = lists[0];
        int skipIndex = 0;

        /**
         * 找出val 最小的 listnode
         */
        for (int i = 1; i<lists.length; i++){
            if (lists[i].val < dummy.val){
                dummy.next = lists[i];
                skipIndex = i;
            }
        }

        for (int i = 0; i<lists.length; i++){
            if (i == skipIndex){
                continue;
            }
            ListNode listNode = lists[i];
            ListNode innerPoint = listNode;
            ListNode point = dummy.next;
            ListNode currentPoint = dummy;

            while (innerPoint != null){
                if (innerPoint.val < point.val){
                    ListNode temp = innerPoint.next;
                    currentPoint.next = innerPoint;

                    currentPoint = innerPoint;
                    innerPoint.next = point;

                    innerPoint = temp;
                }else {
                    while (point !=null){
                        if (innerPoint.val < point.val){
                            ListNode temp = innerPoint.next;

                            innerPoint.next = point;
                            currentPoint.next = innerPoint;
                            currentPoint = currentPoint.next;

                            innerPoint = temp;
                            break;
                        }else {
                            ListNode temp2 = point.next;

                            point.next = innerPoint;
                            currentPoint = point;

                            point = temp2;
                        }
                    }
                }
            }
        }

        return dummy.next;
    }
}
