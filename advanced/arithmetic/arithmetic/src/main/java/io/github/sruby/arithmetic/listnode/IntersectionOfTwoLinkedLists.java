package io.github.sruby.arithmetic.listnode;

/**
 * 160. Intersection of Two Linked Lists
 *
 * @author Sruby
 * @date 7/15/2023 1:51 PM
 */
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB ==null){
            return null;
        }

        ListNode pointA = headA;
        ListNode pointB = headB;

        //不能通过while (pointA !=null && pointB!=null)方式判断，否则无法满足没有相交的案例
//        在没有相交的情况下，pointA和pointB都会为null，从而终止循环，如果使用空判断就会进入死循环
        while (pointA != pointB){
            if (pointA == null){
                pointA = headB;
            }else {
                pointA = pointA.next;
            }

            if (pointB == null){
                pointB = headA;
            }else {
                pointB = pointB.next;
            }
        }

        if (pointA == null || pointB == null){
            return null;
        }

        return pointA;
    }
}
