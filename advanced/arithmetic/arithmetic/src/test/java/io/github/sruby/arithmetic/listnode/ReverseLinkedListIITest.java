package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Sruby
 * @date 7/28/2023 12:30 PM
 */
class ReverseLinkedListIITest {
    private ReverseLinkedListII reverseLinkedListII = new ReverseLinkedListII();

    @Test
    void reverseBetween() {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        ListNode reversedBetween = reverseLinkedListII.reverseBetween(listNode, 2, 4);
        while (reversedBetween !=null){
            System.out.println(reversedBetween.val);
            reversedBetween = reversedBetween.next;
        }
    }


    /**
     * head =
     * [3,5]
     * left =
     * 1
     * right =
     * 1
     */
    @Test
    public void test() {
        ListNode listNode = new ListNode(3);
        listNode.next = new ListNode(5);
        ListNode reversedBetween = reverseLinkedListII.reverseBetween(listNode, 1, 1);
        while (reversedBetween !=null){
            System.out.println(reversedBetween.val);
            reversedBetween = reversedBetween.next;
        }
    }
}