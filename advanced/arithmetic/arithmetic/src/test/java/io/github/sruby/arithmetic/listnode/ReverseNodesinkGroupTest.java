package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Test;

/**
 * test
 *
 * @author Sruby
 * @date 1/8/2023 17:10
 */
class ReverseNodesinkGroupTest {
    private ReverseNodesInKGroup reverseNodesinkGroup = new ReverseNodesInKGroup();
    @Test
    public void test() {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        ListNode reverseKGroup = reverseNodesinkGroup.reverseKGroup(listNode, 2);

        while (reverseKGroup !=null){
            System.out.println(reverseKGroup.val);
            reverseKGroup = reverseKGroup.next;
        }


    }

}