package two_pointers;

/**
 * Problem: LeetCode 11 - Container With Most Water
 * URL: https://leetcode.com/problems/container-with-most-water/
 * Difficulty: Medium
 * Pattern: Two Pointers (Greedy)
 *
 * Approach:
 * - Initialize two pointers: left at the start (0) and right at the end (n - 1).
 * - The area is determined by: width * min(height[left], height[right]).
 * - To maximize area, always move the pointer pointing to the shorter line inward, 
 *   as moving the taller line can never produce a larger area with a smaller width.
 * - Track and update the maximum area found so far.
 *
 * Complexity:
 * - Time Complexity: O(n) — each element is visited at most once as pointers converge.
 * - Space Complexity: O(1) — constant extra space used.
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int maxWater = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            int currentArea = width * currentHeight;

            maxWater = Math.max(maxWater, currentArea);

            // Greedily shift the pointer with the smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }

    // Optional main method for quick local testing
    public static void main(String[] args) {
        ContainerWithMostWater solver = new ContainerWithMostWater();
        
        int[] example1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Max Area: " + solver.maxArea(example1)); // Output: 49

        int[] example2 = {1, 1};
        System.out.println("Max Area: " + solver.maxArea(example2)); // Output: 1
    }
}
