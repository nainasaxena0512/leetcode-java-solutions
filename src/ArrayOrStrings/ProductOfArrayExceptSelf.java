package ArrayOrStrings;

import java.util.Arrays;

// ms practice
// 1. The Core Concept:
// Given an integer array nums, return an array answer such that answer[i] is equal to the product of all elements of nums except nums[i].
// Constraint: You must write an algorithm that runs in O(n) time and WITHOUT using the division operation.
// Follow-up: Can you solve it in O(1) extra space complexity? (The output array does not count as extra space for complexity analysis.)

// 2. The Strategy: Prefix & Suffix Products (Left-to-Right & Right-to-Left Passes)
// For any index i, the product of all numbers except nums[i] is:
// (product of all numbers to the left of i) * (product of all numbers to the right of i).
// - Pass 1 (Left to Right):
//   Build prefix products directly in the result array `ans`.
//   `ans[i]` stores the product of all numbers before index `i` (with `ans[0] = 1`).
// - Pass 2 (Right to Left):
//   Maintain a running `suffixProduct` (initialized to 1).
//   Multiply `ans[i]` by `suffixProduct`, then update `suffixProduct *= nums[i]`.

// Complexity:
// - Time Complexity: O(n) — two linear passes through the array.
// - Space Complexity: O(1) — no extra auxiliary arrays used; output array is modified in-place.

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] ans = new int[n];

        // Pass 1: ans[i] contains the product of all elements to the left of i
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        // Pass 2: Multiply with running product of all elements to the right of i
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * suffixProduct;
            suffixProduct *= nums[i];
        }

        return ans;
    }

    // Optional main method for local verification
    public static void main(String[] args) {
        ProductOfArrayExceptSelf solver = new ProductOfArrayExceptSelf();

        int[] nums1 = {1, 2, 3, 4};
        System.out.println("Result 1: " + Arrays.toString(solver.productExceptSelf(nums1)));
        // Output: [24, 12, 8, 6]

        int[] nums2 = {-1, 1, 0, -3, 3};
        System.out.println("Result 2: " + Arrays.toString(solver.productExceptSelf(nums2)));
        // Output: [0, 0, 9, 0, 0]
    }
}
