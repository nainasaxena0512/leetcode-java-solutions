package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem: LeetCode 20 - Valid Parentheses
 * URL: https://leetcode.com/problems/valid-parentheses/
 * Difficulty: Easy / Fundamental
 * Pattern: Stack (LIFO Matching)
 *
 * Approach:
 * - Use a Deque (ArrayDeque) as a Stack to match corresponding closing brackets.
 * - Iterate through each character in the string:
 *   - For an opening bracket '(', push the expected closing bracket ')' onto the stack.
 *   - For '{', push '}'.
 *   - For '[', push ']'.
 *   - For a closing bracket: check if stack is empty or the popped element does not match.
 * - If the stack is empty at the end, all brackets were matched in the correct order.
 *
 * Complexity:
 * - Time Complexity: O(n) — single pass through the string of length n.
 * - Space Complexity: O(n) — in the worst case (e.g. "((((("), stack stores n characters.
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false; // Odd length strings can never be fully matched
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    // Optional main method for local testing
    public static void main(String[] args) {
        ValidParentheses solver = new ValidParentheses();

        System.out.println("Result 1: " + solver.isValid("()"));       // true
        System.out.println("Result 2: " + solver.isValid("()[]{}"));   // true
        System.out.println("Result 3: " + solver.isValid("(]"));       // false
        System.out.println("Result 4: " + solver.isValid("([)]"));     // false
        System.out.println("Result 5: " + solver.isValid("{[]}"));     // true
    }
}
