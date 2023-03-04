package io.github.sruby.arithmetic.listnode.merge;

import io.github.sruby.arithmetic.listnode.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author Sruby
 * @date 2023-3-4 22:04
 */
class SolutionTest {
    private Solution solution = new Solution();

    @Test
    void mergeKLists() {
        ListNode[] lists = {new ListNode(1),new ListNode(3),new ListNode(5)};
        solution.mergeKLists(lists);
    }
    @Test
    void mergeKLists2() {
        ListNode[] lists = {new ListNode(1),new ListNode(3),new ListNode(5)};
        solution.mergeKLists2(lists);
    }
}