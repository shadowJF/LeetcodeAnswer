package practice;

/**
 *
 * <code>LeetCode Problem 65<code>
 * <p>
 * <li>Validate if a given string is numeric.
 * <li>Some examples:
 * <li>"0" => true
 * <li>" 0.1 " => true
 * <li>"abc" => false
 * <li>"1 a" => false
 * <li>"2e10" => true
 * <li>Note: It is intended for the problem statement to be ambiguous. You should gather all
 * requirements up front before implementing one.
 * <li>Update (2015-02-10):
 * <li>The signature of the C++ function had been updated. If you still see your function signature
 * accepts a const char * argument, please click the reload button to reset your code definition.
 * </p>
 * 
 */

public class LeetCode_65 {

  public static void main(String argv[]) {
    // int[][] tmp = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
    System.out.println(new LeetCode_65().new Solution().isNumber("+.8"));
  }

  /**
   * 这道题我真的放弃。。
   * 
   * 因为我自己都不知道哪些字符串算有效的数字
   * 
   * 后来看了人家的解答，发现需要满足这些特点
   * 
   * .之前不能有e和.
   * 
   * e之前不能有e ,并且必须有数字
   * 
   * +和-只能在首位或者e之后一位
   * 
   * 整个串中必须有数字，e之后必须有数字
   */
  class Solution {
    public boolean isNumber(String s) {
      s = s.trim();
      boolean pointSeen = false;
      boolean eSeen = false;
      boolean numberSeen = false;
      for (int i = 0; i < s.length(); i++) {
        if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
          numberSeen = true;
        } else if (s.charAt(i) == '.') {
          if (eSeen || pointSeen)
            return false;
          pointSeen = true;
        } else if (s.charAt(i) == 'e') {
          if (eSeen || !numberSeen)
            return false;
          numberSeen = false;
          eSeen = true;
        } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
          if (i != 0 && s.charAt(i - 1) != 'e')
            return false;
        } else
          return false;
      }
      return numberSeen;
    }
  }

}
