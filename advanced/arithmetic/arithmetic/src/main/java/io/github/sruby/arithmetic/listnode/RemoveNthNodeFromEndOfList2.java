package io.github.sruby.arithmetic.listnode;

/**
 * 19. Remove Nth Node From End of List
 * 遍历1遍的解法
 *
 * @author Sruby
 * @date 7/13/2023 5:41 PM
 */
public class RemoveNthNodeFromEndOfList2 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null){
            return null;
        }
        //处理删除第一个元素的场景
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        /**
         * 找到倒数n+1个元素
         */
        ListNode firstPoint = dummy;
        for (int i = 0; i < n+1; i++){
            firstPoint = firstPoint.next;
            //题目存在n不超过list长度的约束
//            如果需要添加约束不能使用以下逻辑，比如head = [1]， n=1,会导致出错
//            if (firstPoint == null){
//                return dummy.next;
//            }
        }

        ListNode lastPoint = dummy;
        while (firstPoint !=null){
            firstPoint = firstPoint.next;
            lastPoint = lastPoint.next;
        }
        lastPoint.next = lastPoint.next.next;

        return dummy.next;
    }
}
