package ArrayOrStrings;

import java.util.Arrays;

// ms practice
// 1. The Core Concept:
// Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
// The order of the elements may be changed. Then return the number of elements in nums which are not equal to val (k).
// Requirement: Modifying the input array in-place with O(1) extra memory.

// 2. The Strategy: Two Pointers (Reader & Writer)
// - Pointer 'k' (writer pointer): Keeps track of the index where the next valid (non-val) element should be placed.
// - Pointer 'i' (reader pointer): Scans through the entire array.
// - Whenever nums[i] != val, we assign nums[k] = nums[i] and increment k.
// - At the end of the loop, k represents the number of elements not equal to val, and the first k elements contain the result.

// Complexity:
// - Time Complexity: O(n) — single pass where n is the length of nums.
// - Space Complexity: O(1) — in-place modification using constant extra space.

public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int k = 0; // Index for placing non-val elements

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }

    // Optional main method for local testing
    public static void main(String[] args) {
        RemoveElement solver = new RemoveElement();

        int[] nums1 = {3, 2, 2, 3};
        int val1 = 3;
        int k1 = solver.removeElement(nums1, val1);
        System.out.println("k = " + k1 + ", nums = " + Arrays.toString(Arrays.copyOf(nums1, k1)));
        // Output: k = 2, nums = [2, 2]

        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;
        int k2 = solver.removeElement(nums2, val2);
        System.out.println("k = " + k2 + ", nums = " + Arrays.toString(Arrays.copyOf(nums2, k2)));
        // Output: k = 5, nums = [0, 1, 3, 0, 4]
    }
}
