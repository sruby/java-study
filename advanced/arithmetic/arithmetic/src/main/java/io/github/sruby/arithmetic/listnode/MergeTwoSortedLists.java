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
        //不用新建两个指针，list1 和 list2 本身已经是指针
//        ListNode p1 = list1;
//        ListNode p2 = list2;

        while ( list1 !=null && list2 !=null){
            if (list1.val > list2.val){
                p.next = list2;
                list2 = list2.next;
            }else {
                p.next = list1;
                list1 = list1.next;
            }
            p = p.next;
        }

        if (list2 == null){
            p.next = list1;
        }

        if (list1 == null){
            p.next = list2;
        }

        return dummy.next;
    }
}
