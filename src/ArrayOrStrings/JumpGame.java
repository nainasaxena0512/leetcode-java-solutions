package ArrayOrStrings;

// ms practice
// 1. The Core Concept:
// You are given an integer array `nums`. You are initially positioned at the array's first index (index 0),
// and each element in the array represents your maximum jump length at that position.
// Return true if you can reach the last index, or false otherwise.

// 2. The Strategy: Greedy / Maximum Reachable Index
// - Maintain a variable `maxReach` representing the furthest index we can currently reach.
// - Iterate through the array using index `i`:
//   - If the current index `i` is greater than `maxReach`, it means we are stuck at a point we cannot reach -> return false.
//   - Update `maxReach = Math.max(maxReach, i + nums[i])`.
//   - If `maxReach` is greater than or equal to `nums.length - 1`, we can reach the end -> return true early.
// - If the loop finishes, check if `maxReach >= nums.length - 1`.

// Complexity:
// - Time Complexity: O(n) — single pass through the array.
// - Space Complexity: O(1) — constant extra space using a single integer tracking variable.

public class JumpGame {

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {
            // If the current index is beyond the furthest reachable point, we cannot proceed
            if (i > maxReach) {
                return false;
            }

            // Update the furthest index reachable from here
            maxReach = Math.max(maxReach, i + nums[i]);

            // Early exit if the last index is already reachable
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }

    // Optional main method for local verification
    public static void main(String[] args) {
        JumpGame solver = new JumpGame();

        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Can Jump 1: " + solver.canJump(nums1)); 
        // Output: true (Jump 1 step from 0 to 1, then 3 steps to the last index)

        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Can Jump 2: " + solver.canJump(nums2)); 
        // Output: false (All paths lead to index 3 with value 0, unable to reach index 4)
    }
}
