package ArrayOrStrings;

// ms practice
// 1. The Core Concept:
// Given n non-negative integers representing an elevation map where the width of each bar is 1,
// compute how much water it can trap after raining.
// Formula for water trapped at index i:
// water[i] = Math.max(0, Math.min(maxLeft, maxRight) - height[i])

// 2. The Strategy: Two Pointers (O(1) Space Optimization)
// - We use two pointers: `left = 0` and `right = height.length - 1`.
// - Maintain `leftMax` and `rightMax` to track the highest bar seen from both sides.
// - At any step, if `leftMax < rightMax`, the bottleneck for the left position is guaranteed to be `leftMax`
//   (since there exists a wall on the right that is at least `rightMax >= leftMax`).
//   We can safely compute trapped water at `left` and advance `left++`.
// - Otherwise, the bottleneck is `rightMax`, so we compute trapped water at `right` and advance `right--`.

// Complexity:
// - Time Complexity: O(n) — single pass where left and right pointers meet.
// - Space Complexity: O(1) — constant extra space using only pointer and max tracking variables.

public class TrappingRainWater {

    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left]; // Update max wall on left
                } else {
                    totalWater += leftMax - height[left]; // Trap water at left
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right]; // Update max wall on right
                } else {
                    totalWater += rightMax - height[right]; // Trap water at right
                }
                right--;
            }
        }

        return totalWater;
    }

    // Optional main method for local testing
    public static void main(String[] args) {
        TrappingRainWater solver = new TrappingRainWater();

        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Trapped Water 1: " + solver.trap(height1)); // Output: 6

        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println("Trapped Water 2: " + solver.trap(height2)); // Output: 9
    }
}
