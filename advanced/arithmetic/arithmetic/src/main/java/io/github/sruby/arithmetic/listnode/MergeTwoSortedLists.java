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

        while ( list1 !=null && list2 !=null){
            if (list1.val > list2.val){
                dummy.next = list2;
                list2 = list2.next;
            }else {
                dummy.next = list1;
                list1 = list1.next;
            }

//            if ()
        }

        return dummy.next;

    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);

        ListNode p1 = listNode1;

        ListNode dump = new ListNode(-1);

        dump.next = p1;
        p1 = listNode1.next;

        System.out.println(dump.next.val); // 1
        System.out.println(p1 == null);  //true
    }
}
