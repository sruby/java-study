package io.github.sruby.arithmetic.listnode;

/**
 * 21. Merge Two Sorted Lists
 *
 * @author Sruby
 * @date 30/6/2023 18:08
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }

        if (list2 == null){
            return list1;
        }

        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = list1;
        ListNode p2 = list2;

        while ( p1 !=null && p2 !=null){
            // 比较 p1 和 p2 两个指针
            // 将值较小的的节点接到 p 指针
            if (p1.val > p2.val){
                p.next = p2;
                p2 = p2.next;
            }else {
                p.next = p1;
                p1 = p1.next;
            }
            // p 指针不断前进
            p = p.next;
        }

        if (p1 == null){
            p.next = p2;
        }

        if (p2 == null){
            p.next = p1;
        }

        return dummy.next;
    }
}
