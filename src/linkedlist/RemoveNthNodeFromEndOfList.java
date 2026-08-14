package linkedlist;

/**
 * Problem: LeetCode 19 - Remove Nth Node From End of List
 * URL: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * Difficulty: Medium
 * Pattern: Linked List / Two Pointers (Fixed Gap Technique)
 *
 * Approach:
 * - Use a dummy node pointing to head to gracefully handle edge cases (e.g., removing the head node).
 * - Initialize two pointers: 'fast' and 'slow', both starting at the dummy node.
 * - Advance 'fast' pointer forward by (n + 1) steps so the gap between 'fast' and 'slow' is exactly (n + 1).
 * - Move both 'fast' and 'slow' forward together one step at a time until 'fast' reaches null.
 * - At this point, 'slow' points directly to the node right BEFORE the target node to delete.
 * - Delete the node by setting: slow.next = slow.next.next.
 * - Return dummy.next.
 *
 * Complexity:
 * - Time Complexity: O(L) — single pass where L is the total number of nodes in the list.
 * - Space Complexity: O(1) — constant extra space using only pointer references.
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // Advance fast pointer so that the gap between fast and slow is (n + 1)
        for (int i = 0; i <= n; i++) {
            if (fast == null) {
                return head; // n is larger than the list length
            }
            fast = fast.next;
        }

        // Move both pointers until fast hits the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Unlink the nth node from the end
        slow.next = slow.next.next;

        return dummy.next;
    }

    // Helper to print linked list for testing
    private static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        System.out.println(sb.toString());
    }

    // Optional main method for local verification
    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList solver = new RemoveNthNodeFromEndOfList();

        // List: 1 -> 2 -> 3 -> 4 -> 5, n = 2
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.print("Original list: ");
        printList(head);

        ListNode result = solver.removeNthFromEnd(head, 2);
        System.out.print("After removing 2nd from end: ");
        printList(result); // Expected: 1 -> 2 -> 3 -> 5
    }
}
