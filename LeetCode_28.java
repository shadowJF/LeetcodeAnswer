package practice;

/**
 *
 * <code>LeetCode Problem 28<code>
 * <p>
 * <li>Implement strStr().
 * <li>Return the index of the first occurrence of needle in haystack, or -1 if needle is not part
 * of haystack.
 * <li>Example 1:
 * <li>Input: haystack = "hello", needle = "ll"
 * <li>Output: 2
 * <li>Example 2:
 * <li>Input: haystack = "aaaaa", needle = "bba"
 * <li>Output: -1
 * </p>
 * 
 */

public class LeetCode_28 {

  public static void main(String argv[]) {

  }

  /**
   * 这道题就是O(mn)的时间复杂度，不要想的太复杂，就是一个double循环解决
   */
  class Solution {
    public int strStr(String haystack, String needle) {
      if (needle.equals(""))
        return 0;
      for (int i = 0; i < haystack.length(); i++) {
        int index1 = i;
        int index2 = 0;
        while (index1 < haystack.length() && index2 < needle.length()
            && haystack.charAt(index1) == needle.charAt(index2)) {
          index1++;
          index2++;
        }
        if (index2 == needle.length())
          return i;
      }
      return -1;
    }
  }

}
