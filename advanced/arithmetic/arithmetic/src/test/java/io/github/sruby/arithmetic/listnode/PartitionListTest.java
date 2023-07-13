package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Sruby
 * @date 11/7/2023 15:17
 */
class PartitionListTest {

    private PartitionList partitionList = new PartitionList();

    @Test
    void partition() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        ListNode partition = partitionList.partition(head, 3);
        while (partition !=null){
            System.out.println(partition.val);
            partition = partition.next;
        }

    }
}