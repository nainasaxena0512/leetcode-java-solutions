package hashmap;

/**
 * Problem: LeetCode 242 - Valid Anagram
 * URL: https://leetcode.com/problems/valid-anagram/
 * Difficulty: Easy / Fundamental
 * Pattern: Hash Map / Frequency Array
 *
 * Approach:
 * - If strings differ in length, they cannot be anagrams -> return false immediately.
 * - For lowercase English letters ('a' - 'z'), a fixed-size integer array of size 26
 *   serves as an optimal, low-overhead direct hash map.
 * - Increment frequency counts for characters in string `s` and decrement for string `t`.
 * - If all frequency counts are 0, the two strings are valid anagrams.
 *
 * Complexity:
 * - Time Complexity: O(n) — single pass where n is string length.
 * - Space Complexity: O(1) — constant space with fixed array size of 26.
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        int[] charCounts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
            charCounts[t.charAt(i) - 'a']--;
        }

        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    // Optional main method for local verification
    public static void main(String[] args) {
        ValidAnagram solver = new ValidAnagram();

        System.out.println(solver.isAnagram("anagram", "nagaram")); // Output: true
        System.out.println(solver.isAnagram("rat", "car"));         // Output: false
    }
}
