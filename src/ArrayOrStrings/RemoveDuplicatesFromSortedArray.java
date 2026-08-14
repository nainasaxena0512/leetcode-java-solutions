package ArrayOrStrings;

import java.util.Arrays;

// ms practice
// 1. The Core Concept:
// Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place
// such that each unique element appears only once. The relative order of the elements should be kept the same.
// Return the number of unique elements (k).
// Requirement: Must modify the input array in-place with O(1) extra memory.

// 2. The Strategy: Fast & Slow Pointer (Reader & Writer)
// - Since the array is already sorted, all duplicates are adjacent.
// - The first element nums[0] is always unique, so initialize the writer pointer `insertIndex = 1`.
// - Iterate the reader pointer `i` from 1 to nums.length - 1:
//   - If nums[i] != nums[i - 1], we have encountered a new unique element.
//   - Write nums[i] to nums[insertIndex] and increment `insertIndex`.
// - At the end, `insertIndex` is the count of unique elements, and nums[0...insertIndex-1] holds them.

// Complexity:
// - Time Complexity: O(n) — single pass where n is the length of the array.
// - Space Complexity: O(1) — in-place modification using constant extra space.

public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int insertIndex = 1; // Tracks where to write the next unique element

        for (int i = 1; i < nums.length; i++) {
            // Found a new unique element
            if (nums[i] != nums[i - 1]) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }

        return insertIndex;
    }

    // Optional main method for local testing
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray solver = new RemoveDuplicatesFromSortedArray();

        int[] nums1 = {1, 1, 2};
        int k1 = solver.removeDuplicates(nums1);
        System.out.println("k = " + k1 + ", nums = " + Arrays.toString(Arrays.copyOf(nums1, k1)));
        // Output: k = 2, nums = [1, 2]

        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k2 = solver.removeDuplicates(nums2);
        System.out.println("k = " + k2 + ", nums = " + Arrays.toString(Arrays.copyOf(nums2, k2)));
        // Output: k = 5, nums = [0, 1, 2, 3, 4]
    }
}
