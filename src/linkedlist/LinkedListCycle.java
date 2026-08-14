package linkedlist;

/**
 * Problem: LeetCode 141 - Linked List Cycle
 * URL: https://leetcode.com/problems/linked-list-cycle/
 * Difficulty: Easy / Fundamental
 * Pattern: Fast & Slow Pointers (Floyd's Tortoise and Hare Algorithm)
 *
 * Approach:
 * - Use two pointers starting at the head node:
 *   - 'slow' pointer moves 1 step at a time.
 *   - 'fast' pointer moves 2 steps at a time.
 * - If there is no cycle, 'fast' (or 'fast.next') will eventually hit null -> return false.
 * - If there is a cycle, the fast pointer will enter the cycle and catch up to the slow pointer,
 *   causing them to eventually point to the exact same node (fast == slow) -> return true.
 *
 * Complexity:
 * - Time Complexity: O(n) — where n is the number of nodes in the linked list.
 * - Space Complexity: O(1) — constant extra space using only two pointers (no HashSet required).
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;         // Move 1 step
            fast = fast.next.next;    // Move 2 steps

            // Cycle detected: fast caught up with slow
            if (slow == fast) {
                return true;
            }
        }

        // Fast pointer reached the end, so there is no cycle
        return false;
    }

    // Optional main method for local testing
    public static void main(String[] args) {
        LinkedListCycle solver = new LinkedListCycle();

        // Creating list: 3 -> 2 -> 0 -> -4 -> (cycles back to node 2)
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode nodeMinus4 = new ListNode(-4);

        head.next = node2;
        node2.next = node0;
        node0.next = nodeMinus4;
        nodeMinus4.next = node2; // Cycle here

        System.out.println("Has Cycle: " + solver.hasCycle(head)); // Output: true
    }
}
