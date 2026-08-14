package sliding_window;

/**
 * Problem: LeetCode 209 - Minimum Size Subarray Sum
 * URL: https://leetcode.com/problems/minimum-size-subarray-sum/
 * Difficulty: Medium
 * Pattern: Sliding Window (Dynamic / Variable Size)
 *
 * Approach:
 * - Use a dynamic sliding window maintained by two pointers: 'left' and 'right'.
 * - Expand the window by moving 'right' and adding nums[right] to the running sum.
 * - Once the running sum >= target, contract the window from the left to find 
 *   the minimal valid length while maintaining the condition (sum >= target).
 * - Keep updating minLength with the current window size (right - left + 1).
 *
 * Complexity:
 * - Time Complexity: O(n) — each element is added and removed from the window at most once.
 * - Space Complexity: O(1) — only pointers and running sum variables are used.
 */
public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int minLength = Integer.MAX_VALUE;
        int currentSum = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            // Shrink window from the left as long as the sum condition is satisfied
            while (currentSum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
        }

        // If no valid subarray was found, return 0
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // Optional main method for testing
    public static void main(String[] args) {
        MinimumSizeSubarraySum solver = new MinimumSizeSubarraySum();

        int target1 = 7;
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        System.out.println("Result: " + solver.minSubArrayLen(target1, nums1)); // Output: 2 ([4, 3])

        int target2 = 4;
        int[] nums2 = {1, 4, 4};
        System.out.println("Result: " + solver.minSubArrayLen(target2, nums2)); // Output: 1 ([4])

        int target3 = 11;
        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println("Result: " + solver.minSubArrayLen(target3, nums3)); // Output: 0
    }
}
