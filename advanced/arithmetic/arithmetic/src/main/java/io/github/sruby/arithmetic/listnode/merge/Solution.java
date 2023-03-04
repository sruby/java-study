package io.github.sruby.arithmetic.listnode.merge;

import io.github.sruby.arithmetic.listnode.ListNode;

import java.util.PriorityQueue;

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        // 优先级队列，最小堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            lists.length, (a, b)->(a.val - b.val));
        // 将 k 个链表的头结点加入最小堆
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        while (!pq.isEmpty()) {
            // 获取最小节点，接到结果链表中
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null) {
                pq.add(node.next);
            }
            // p 指针不断前进
            p = p.next;
        }
        return dummy.next;
    }
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                lists.length, (a, b)->(a.val - b.val));

        for(ListNode header : lists){
            if(header !=null){
                pq.add(header);
            }
        }

        while(!pq.isEmpty()){
            ListNode minListNode = pq.poll();
            p.next = minListNode;
            if(minListNode.next !=null){
                pq.add(minListNode.next);
            }
            p = p.next;
        }

        return dummy.next;
    }
}
// 详细解析参见：
// https://labuladong.github.io/article/?qno=


