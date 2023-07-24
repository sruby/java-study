package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddTwoNumbersTest {

    @Test
    void testAddTwoNumbers() {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = addTwoNumbers.addTwoNumbers(l1, l2);


        assertEquals(7, result.val);
        assertEquals(0, result.next.val);
        assertEquals(8, result.next.next.val);
    }

    @Test
    void testAddTwoNumbersWithCarry() {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode l1 = new ListNode(5);
        l1.next = new ListNode(8);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(4);

        ListNode result = addTwoNumbers.addTwoNumbers(l1, l2);

        assertEquals(0, result.val);
        assertEquals(3, result.next.val);
        assertEquals(1, result.next.next.val);
    }
}
