package linkedlist;

/**
 * Problem: LeetCode 2 - Add Two Numbers
 * URL: https://leetcode.com/problems/add-two-numbers/
 * Difficulty: Medium
 * Pattern: Linked List (Carry Propagation / Dummy Head)
 *
 * Approach:
 * - Use a dummy head node to easily build and return the resulting linked list.
 * - Maintain a 'carry' variable initialized to 0.
 * - Traverse both lists simultaneously as long as l1 != null, l2 != null, or carry != 0:
 *   - Extract values from l1 and l2 (default to 0 if a list is already exhausted).
 *   - Calculate total sum = val1 + val2 + carry.
 *   - Update carry = sum / 10 and digit node = sum % 10.
 *   - Append the new digit node to the result list and advance pointers.
 *
 * Complexity:
 * - Time Complexity: O(max(m, n)) — where m and n are the lengths of l1 and l2.
 * - Space Complexity: O(max(m, n)) — length of the new result list is at most max(m, n) + 1.
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;

            current.next = new ListNode(sum % 10);
            current = current.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return dummyHead.next;
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

    // Optional main method for local testing
    public static void main(String[] args) {
        AddTwoNumbers solver = new AddTwoNumbers();

        // l1 = [2 -> 4 -> 3] (represents 342)
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        // l2 = [5 -> 6 -> 4] (represents 465)
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        // Expected output: 7 -> 0 -> 8 (represents 807)
        ListNode result = solver.addTwoNumbers(l1, l2);
        System.out.print("Result: ");
        printList(result);
    }
}
