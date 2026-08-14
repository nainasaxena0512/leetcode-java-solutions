package ArrayOrStrings;

// ms practice
// 1. The Core Concept:
// You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
// Each element nums[i] represents the maximum length of a forward jump from index i.
// Return the minimum number of jumps to reach nums[n - 1].
// We are guaranteed that you can always reach the last index.

// 2. The Strategy: Greedy / Implicit BFS Level Window
// - Think of this as a Breadth-First Search (BFS) where each jump represents moving to the next "level".
// - Maintain three variables:
//   - `jumps`: Number of jumps taken so far.
//   - `currentEnd`: The furthest index that can be reached with the current number of jumps (end of current window).
//   - `farthest`: The furthest index that can be reached with one more jump (end of next window).
// - Iterate through the array up to `nums.length - 2` (we don't need to jump from the last element):
//   - At each index `i`, update `farthest = Math.max(farthest, i + nums[i])`.
//   - When `i` reaches `currentEnd`, it means we must make a jump to continue:
//     - Increment `jumps`.
//     - Set `currentEnd = farthest`.

// Complexity:
// - Time Complexity: O(n) — single pass through the array.
// - Space Complexity: O(1) — constant extra space using only three tracking variables.

public class JumpGameII {

    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        // Loop until nums.length - 2 because once we reach or cross the last index, no more jumps needed
        for (int i = 0; i < nums.length - 1; i++) {
            // Update the furthest point we can reach in the next jump
            farthest = Math.max(farthest, i + nums[i]);

            // If we've reached the end of the current jump's reach, we must take another jump
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // Early exit if the current jump can already reach or exceed the last index
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }

        return jumps;
    }

    // Optional main method for local verification
    public static void main(String[] args) {
        JumpGameII solver = new JumpGameII();

        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Min Jumps 1: " + solver.jump(nums1)); 
        // Output: 2 (Jump 1 step from index 0 to 1, then 3 steps to the last index)

        int[] nums2 = {2, 3, 0, 1, 4};
        System.out.println("Min Jumps 2: " + solver.jump(nums2)); 
        // Output: 2
    }
}
