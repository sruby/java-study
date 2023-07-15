package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IntersectionOfTwoLinkedListsTest {
    IntersectionOfTwoLinkedLists solution = new IntersectionOfTwoLinkedLists();

    @Test
    public void testIntersection() {
        // 创建链表
        ListNode common = new ListNode(8);
        common.next = new ListNode(4);
        common.next.next = new ListNode(5);

        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = common;

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(0);
        headB.next.next = new ListNode(1);
        headB.next.next.next = common;

        // 测试
        assertEquals(common, solution.getIntersectionNode(headA, headB));
    }

    @Test
    public void testNoIntersection() {
        // 创建链表
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(9);
        headA.next.next = new ListNode(1);
        headA.next.next.next = new ListNode(2);
        headA.next.next.next.next = new ListNode(4);

        ListNode headB = new ListNode(3);
        headB.next = new ListNode(2);
        headB.next.next = new ListNode(4);

        // 测试
        assertNull(solution.getIntersectionNode(headA, headB));
    }

    @Test
    public void testSameList() {
        // 创建链表
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        // 测试
        assertEquals(head,solution.getIntersectionNode(head, head));
    }

    @Test
    public void testEmptyList() {
        // 创建链表
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        // 测试
        assertNull(solution.getIntersectionNode(head, null));
    }
}
