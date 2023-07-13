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
        // 存放小于 x 的链表的虚拟头结点
        ListNode firstDummy = new ListNode(-1);
        // 存放大于等于 x 的链表的虚拟头结点
        ListNode lastDummy = new ListNode(-1);
        // point指针负责生成结果链表
        ListNode firstPoint = firstDummy;
        ListNode lastPoint = lastDummy;
        // head 负责遍历原链表，类似合并两个有序链表的逻辑
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
        //lastPoint需要把 next 设置为 null（断开原有链表的指针），避免最后带上原有链表的数据
        lastPoint.next = null;

        //因为最终 firstPoint 的 next 会连接到lastDummy,所以不需要断开原有的连接
        // 连接两个链表
        firstPoint.next =  lastDummy.next;

        return firstDummy.next;
    }
}
