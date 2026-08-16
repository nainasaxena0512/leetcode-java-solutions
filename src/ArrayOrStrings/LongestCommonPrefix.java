package ArrayOrStrings;

// ms practice
// 1. The Core Concept:
// Write a function to find the longest common prefix string amongst an array of strings.
// If there is no common prefix, return an empty string "".

// 2. The Strategy: Horizontal Scanning (Prefix Reduction)
// - Initialize `prefix` as the first string `strs[0]`.
// - Iterate through the rest of the strings in the array (from index 1 to n - 1):
//   - While the current string `strs[i]` does not start with `prefix` (i.e. `strs[i].indexOf(prefix) != 0`):
//     - Shorten `prefix` by trimming off its last character: `prefix = prefix.substring(0, prefix.length() - 1)`.
//     - If `prefix` becomes empty, return "" immediately (no common prefix exists).
// - Once all strings have been checked, return the resulting `prefix`.

// Complexity:
// - Time Complexity: O(S) — where S is the sum of all characters in all strings. In the worst case, all n strings are compared.
// - Space Complexity: O(1) — constant extra space used.

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Start by assuming the first string is the common prefix
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            // Trim the prefix until it matches the beginning of strs[i]
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                
                // If the prefix is empty, no common prefix exists
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }

    // Optional main method for local testing
    public static void main(String[] args) {
        LongestCommonPrefix solver = new LongestCommonPrefix();

        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println("Prefix 1: \"" + solver.longestCommonPrefix(strs1) + "\""); // Output: "fl"

        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println("Prefix 2: \"" + solver.longestCommonPrefix(strs2) + "\""); // Output: ""

        String[] strs3 = {"interspecies", "interstellar", "interstate"};
        System.out.println("Prefix 3: \"" + solver.longestCommonPrefix(strs3) + "\""); // Output: "inters"
    }
}
