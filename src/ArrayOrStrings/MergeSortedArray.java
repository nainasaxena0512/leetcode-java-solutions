package ArrayOrStrings;

import java.util.Arrays;

// ms practice
// 1. The Core Concept:
// We are given two sorted integer arrays, nums1 and nums2, and need to merge nums2 into nums1 in non-decreasing order.
// nums1 has a length of m + n, where the first m elements are valid numbers and the last n elements are empty buffer space (0s).

// 2. The Strategy: Why Fill from the Back (Right to Left)?
// If we fill from the front (left to right), writing elements into nums1 would overwrite unprocessed elements, requiring extra space.
// By starting from the back (index m + n - 1):
// - We place the largest element between nums1[i] and nums2[j] at index k.
// - This guarantees we never overwrite unread elements in nums1.

// Complexity:
// - Time Complexity: O(m + n) — single pass backwards across both arrays.
// - Space Complexity: O(1) — in-place modification.

public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;     // End of valid elements in nums1
        int j = n - 1;     // End of nums2
        int k = m + n - 1; // End of nums1's total capacity

        // While there are elements in both arrays
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // If nums2 has remaining elements, copy them
        // (If nums1 has remaining elements, they are already in place)
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    // Alternative Approach: Copy and Sort (Time: O((m+n) log(m+n)), Space: O(1))
    /*
    public void simpleMerge(int[] nums1, int m, int[] nums2, int n) {
        // 1. Copy nums2 into the empty tail of nums1
        for (int idx = 0; idx < n; idx++) {
            nums1[m + idx] = nums2[idx];
        }

        // 2. Let the built-in Dual-Pivot Quicksort do the work
        Arrays.sort(nums1);
    }
    */

    // Optional main method for local verification
    public static void main(String[] args) {
        MergeSortedArray solver = new MergeSortedArray();

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        solver.merge(nums1, m, nums2, n);
        System.out.println("Merged Result: " + Arrays.toString(nums1)); 
        // Expected output: [1, 2, 2, 3, 5, 6]
    }
}
