package hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: LeetCode 219 - Contains Duplicate II
 * URL: https://leetcode.com/problems/contains-duplicate-ii/
 * Difficulty: Easy
 * Pattern: Hash Map (Last Seen Index Tracking / Sliding Window)
 *
 * Approach:
 * - Use a HashMap to store each number and its most recent index: Map<Integer, Integer>.
 * - As we iterate through the array:
 *   - If the number exists in the map and the index difference (i - map.get(nums[i])) <= k, return true.
 *   - Always update the map with the current index (so we always compare against the closest duplicate).
 * - If the loop completes without finding such a pair, return false.
 *
 * Complexity:
 * - Time Complexity: O(n) — single pass through the array with O(1) average lookup/insertion.
 * - Space Complexity: O(min(n, k)) — map stores at most n unique elements.
 */
public class ContainsDuplicateII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k <= 0) {
            return false;
        }

        Map<Integer, Integer> lastSeenIndexMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (lastSeenIndexMap.containsKey(nums[i])) {
                int previousIndex = lastSeenIndexMap.get(nums[i]);
                if (i - previousIndex <= k) {
                    return true;
                }
            }
            lastSeenIndexMap.put(nums[i], i);
        }

        return false;
    }

    // Optional main method for quick local testing
    public static void main(String[] args) {
        ContainsDuplicateII solver = new ContainsDuplicateII();

        int[] nums1 = {1, 2, 3, 1};
        int k1 = 3;
        System.out.println("Result 1: " + solver.containsNearbyDuplicate(nums1, k1)); // true (indices 0 and 3, diff = 3)

        int[] nums2 = {1, 0, 1, 1};
        int k2 = 1;
        System.out.println("Result 2: " + solver.containsNearbyDuplicate(nums2, k2)); // true (indices 2 and 3, diff = 1)

        int[] nums3 = {1, 2, 3, 1, 2, 3};
        int k3 = 2;
        System.out.println("Result 3: " + solver.containsNearbyDuplicate(nums3, k3)); // false
    }
}
