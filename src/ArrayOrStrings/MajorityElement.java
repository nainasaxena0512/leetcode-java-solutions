package ArrayOrStrings;

// ms practice
// 1. The Core Concept:
// Given an array nums of size n, return the majority element.
// The majority element is the element that appears more than ⌊n / 2⌋ times.
// We are guaranteed that the majority element always exists in the array.

// 2. The Strategy: Boyer-Moore Voting Algorithm
// - We maintain a candidate element and a count.
// - As we iterate through the array:
//   - If count reaches 0, we select the current number as the new candidate.
//   - If the current number matches the candidate, increment count.
//   - If it differs, decrement count.
// - Since the majority element appears more than n / 2 times, its frequency will always
//   outweigh all other elements combined, ensuring it remains the candidate at the end.

// Complexity:
// - Time Complexity: O(n) — single pass through the array.
// - Space Complexity: O(1) — constant extra space using only two variables.

public class MajorityElement {

    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 0;

        for (int num : nums) {
            // Pick new candidate when counter hits 0
            if (count == 0) {
                candidate = num;
            }

            // Increment if same as candidate, otherwise decrement
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }

    // Optional main method for local testing
    public static void main(String[] args) {
        MajorityElement solver = new MajorityElement();

        int[] nums1 = {3, 2, 3};
        System.out.println("Majority Element 1: " + solver.majorityElement(nums1)); // Output: 3

        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Majority Element 2: " + solver.majorityElement(nums2)); // Output: 2
    }
}
