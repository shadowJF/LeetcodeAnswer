package practice;

/**
 *
 * <code>LeetCode Problem 58<code>
 * <p>
 * <li>Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word in the string.
 * <li>If the last word does not exist, return 0.
 * <li>Note: A word is defined as a character sequence consists of non-space characters only.
 * <li>Example:
 * <li>Input: "Hello World"
 * <li>Output: 5
 * </p>
 * 
 */

public class LeetCode_58 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_58().new Solution().lengthOfLastWord(""));
  }

  class Solution {
    public int lengthOfLastWord(String s) {
      int len = 0;
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' '))
          len = 1;
        else
          len = s.charAt(i) == ' ' ? len : len + 1;
      }
      return len;
    }
  }
}
