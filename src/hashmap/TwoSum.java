package hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem: LeetCode 1 - Two Sum
 * URL: https://leetcode.com/problems/two-sum/
 * Difficulty: Easy / Fundamental
 * Pattern: Hash Map (One-Pass Complement Lookup)
 *
 * Approach:
 * - Use a HashMap to store array values and their corresponding indices.
 * - For each element nums[i], calculate the required complement: (target - nums[i]).
 * - If the complement already exists in the map, return [map.get(complement), i].
 * - Otherwise, insert the current number and index into the map and continue.
 *
 * Complexity:
 * - Time Complexity: O(n) — single pass through the array with O(1) average lookup time.
 * - Space Complexity: O(n) — to store at most n elements in the HashMap.
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            map.put(nums[i], i);
        }

        return new int[0];
    }

    // Optional main method for quick local verification
    public static void main(String[] args) {
        TwoSum solver = new TwoSum();

        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Result 1: " + Arrays.toString(solver.twoSum(nums1, target1))); // [0, 1]

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("Result 2: " + Arrays.toString(solver.twoSum(nums2, target2))); // [1, 2]

        int[] nums3 = {3, 3};
        int target3 = 6;
        System.out.println("Result 3: " + Arrays.toString(solver.twoSum(nums3, target3))); // [0, 1]
    }
}
