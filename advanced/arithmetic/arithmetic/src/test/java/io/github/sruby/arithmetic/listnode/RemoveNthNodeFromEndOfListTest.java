package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemoveNthNodeFromEndOfListTest {
    @Test
    public void testRemoveNthFromEnd() {
        // 创建一个链表: 1->2->3->4->5
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        RemoveNthNodeFromEndOfList removeNthNodeFromEndOfList = new RemoveNthNodeFromEndOfList();

        // 删除倒数第2个节点，预期结果为: 1->2->3->5
        ListNode result = removeNthNodeFromEndOfList.removeNthFromEnd(head, 2);
        Assertions.assertEquals(1, result.val);
        Assertions.assertEquals(2, result.next.val);
        Assertions.assertEquals(3, result.next.next.val);
        Assertions.assertEquals(5, result.next.next.next.val);
        Assertions.assertNull(result.next.next.next.next);
        
        // 测试链表为空的情况
        result = removeNthNodeFromEndOfList.removeNthFromEnd(null, 1);
        Assertions.assertNull(result);

        // 测试n大于链表长度的情况
        result = removeNthNodeFromEndOfList.removeNthFromEnd(head, 10);
        Assertions.assertNull(result);
    }
}
