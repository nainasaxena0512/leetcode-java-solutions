package sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: LeetCode 3 - Longest Substring Without Repeating Characters
 * URL: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Difficulty: Medium
 * Pattern: Sliding Window (Dynamic with Hash Map index jumping)
 *
 * Approach:
 * - Maintain a sliding window [left, right] using two pointers.
 * - Use a Hash Map to store the last seen index of each character.
 * - As 'right' traverses the string:
 *   - If the character was seen inside the current window (index >= left), 
 *     jump 'left' directly to (last_seen_index + 1) to eliminate the duplicate in O(1).
 *   - Update the character's last seen index in the map.
 *   - Update maxLength with (right - left + 1).
 *
 * Complexity:
 * - Time Complexity: O(n) — single pass of the string where each character is inspected once.
 * - Space Complexity: O(min(m, n)) — where n is string length and m is character set size (up to 128 for ASCII).
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> charLastIndexMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If the character is already inside the current window, shift left pointer
            if (charLastIndexMap.containsKey(currentChar)) {
                left = Math.max(left, charLastIndexMap.get(currentChar) + 1);
            }

            charLastIndexMap.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Optional main method for local verification
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solver = 
            new LongestSubstringWithoutRepeatingCharacters();

        System.out.println(solver.lengthOfLongestSubstring("abcabcbb")); // Output: 3 ("abc")
        System.out.println(solver.lengthOfLongestSubstring("bbbbb"));    // Output: 1 ("b")
        System.out.println(solver.lengthOfLongestSubstring("pwwkew"));   // Output: 3 ("wke")
        System.out.println(solver.lengthOfLongestSubstring(""));         // Output: 0
    }
}
