package hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem: LeetCode 49 - Group Anagrams
 * URL: https://leetcode.com/problems/group-anagrams/
 * Difficulty: Medium
 * Pattern: Hash Map (Sorted String as Key)
 *
 * Approach:
 * - For each string in the input array, sort its characters alphabetically.
 * - All anagrams will produce the exact same sorted string (canonical key).
 * - Use a HashMap where the key is the sorted string and the value is a List of original strings.
 * - Group all matching anagrams under their sorted key, then return all map values.
 *
 * Complexity:
 * - Time Complexity: O(n * k log k) — where n is the number of strings and k is the maximum length of a string (due to sorting each word).
 * - Space Complexity: O(n * k) — to store all strings and keys in the HashMap.
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return ans;
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            String sortString = new String(temp);

            map.putIfAbsent(sortString, new ArrayList<>());
            map.get(sortString).add(str);
        }

        return new ArrayList<>(map.values());
    }

    // Optional main method for quick local testing
    public static void main(String[] args) {
        GroupAnagrams solver = new GroupAnagrams();

        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = solver.groupAnagrams(input);

        System.out.println("Grouped Anagrams: " + result);
    }
}
