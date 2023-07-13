package io.github.sruby.arithmetic.listnode;

import java.util.Comparator;
import java.util.PriorityQueue;

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
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);

        ListNode point = dummy;
        // 优先级队列，最小堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(listNode -> listNode.val));
        // 将 k 个链表的头结点加入最小堆
        for (ListNode head :lists){
            if (head !=null){
                pq.add(head);
            }
        }

        while (!pq.isEmpty()){
            // 获取最小节点，接到结果链表中
            ListNode listNode = pq.poll();
            point.next = listNode;
            if (listNode.next !=null){
                pq.add(listNode.next);
            }
            point = point.next;
        }

        return dummy.next;
    }
}
