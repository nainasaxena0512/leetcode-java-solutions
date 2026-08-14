package stack;

import java.util.Stack;

/**
 * Problem: LeetCode 155 - Min Stack
 * URL: https://leetcode.com/problems/min-stack/
 * Difficulty: Medium
 * Pattern: Stack (Pair / Tracking Min at each State)
 *
 * Why the Pair class?
 * - Without storing the minimum alongside each entry, finding the minimum would require
 *   iterating through the entire stack, taking O(n) time.
 * - By storing the minimum inside a Pair (actual value, minimum so far), getMin() simply
 *   looks at the top of the stack and returns the minimum in O(1) time.
 *
 * Complexity:
 * - push(val): O(1) time
 * - pop(): O(1) time
 * - top(): O(1) time
 * - getMin(): O(1) time
 * - Space Complexity: O(n) — to store pairs in the stack.
 */
class Pair {
    int key;
    int val;

    public Pair(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public int getValue() {
        return this.val;
    }

    public int getkey() {
        return this.key;
    }
}

public class MinStack {

    private final Stack<Pair> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new Pair(val, val));
        } else {
            int currentMin = stack.peek().getValue();
            stack.push(new Pair(val, Math.min(val, currentMin)));
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek().getkey();
        }
        return -1;
    }

    public int getMin() {
        if (!stack.isEmpty()) {
            return stack.peek().getValue();
        }
        return -1;
    }

    // Optional main method for local testing
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println("Current Min: " + minStack.getMin()); // Returns -3
        minStack.pop();
        System.out.println("Top Element: " + minStack.top());    // Returns 0
        System.out.println("Current Min: " + minStack.getMin()); // Returns -2
    }
}
