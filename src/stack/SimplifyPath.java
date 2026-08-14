package stack;

import java.util.Stack;

/**
 * Problem: LeetCode 71 - Simplify Path
 * URL: https://leetcode.com/problems/simplify-path/
 * Difficulty: Medium
 * Pattern: Stack (Unix Path Canonicalization)
 *
 * Why a Stack?
 * A Stack follows the LIFO (Last-In, First-Out) principle, which mirrors directory navigation:
 * 1. Going Deeper (push): When you encounter a regular folder name (like home or user),
 *    you move inside it and add it to your current path history.
 * 2. Going Backward (pop): When you encounter "..", it means "go up one level to the parent directory."
 *    The directory you leave is the most recent one you entered. The stack pops that last-entered
 *    directory immediately.
 *
 * Complexity:
 * - Time Complexity: O(n) — single pass through path components after splitting.
 * - Space Complexity: O(n) — to store directory tokens in the stack and split array.
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        // Line 1: Split the path string into an array of components using "/" as the delimiter
        String[] components = path.split("/");
        
        // Line 2: Initialize a Stack to track valid directory names
        Stack<String> stack = new Stack<>();
        
        // Line 3: Iterate through each directory/command component one by one
        for (String portion : components) {
            
            // Line 4: If the component is "..", we need to move up to the parent directory
            if (portion.equals("..")) {
                // Line 5: Only pop if the stack is not empty (cannot go above root directory)
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } 
            // Line 6: Skip empty strings (caused by '//') and '.' (current directory)
            else if (portion.equals("") || portion.equals(".")) {
                continue;
            } 
            // Line 7: If it's a valid directory name, push it onto the stack
            else {
                stack.push(portion);
            }
        }
        
        // Line 8: Build the final canonical path from the elements in the stack
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        
        // Line 9: If the stack was empty, return "/". Otherwise, return the built path.
        return result.length() == 0 ? "/" : result.toString();
    }

    // Optional main method for local testing
    public static void main(String[] args) {
        SimplifyPath solver = new SimplifyPath();

        System.out.println("Result 1: " + solver.simplifyPath("/home/"));               // "/home"
        System.out.println("Result 2: " + solver.simplifyPath("/home//foo/"));           // "/home/foo"
        System.out.println("Result 3: " + solver.simplifyPath("/home/user/Documents/../Pictures")); // "/home/user/Pictures"
        System.out.println("Result 4: " + solver.simplifyPath("/../"));                  // "/"
        System.out.println("Result 5: " + solver.simplifyPath("/.../a/../b/c/../d/./")); // "/.../b/d"
    }
}
