package practice;

/**
 *
 * <code>LeetCode Problem 67<code>
 * <p>
 * <li>Given two binary strings, return their sum (also a binary string).
 * <li>For example,
 * <li>a = "11"
 * <li>b = "1"
 * <li>Return "100".
 * </p>
 * 
 */

public class LeetCode_67 {

  public static void main(String argv[]) {
    // int[][] tmp = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
    // System.out.println(new LeetCode_66().new Solution().plusOne("+.8"));
  }

  /**
   * 这题就是按照平常的加法的思路去做
   */
  class Solution {
    public String addBinary(String a, String b) {
      StringBuilder sb = new StringBuilder();
      int i = 0;
      int j = 0;
      while (i < a.length() || i < b.length()) {
        int ac = i >= a.length() ? 0 : (a.charAt(a.length() - i - 1) - '0');
        int bc = i >= b.length() ? 0 : (b.charAt(b.length() - i - 1) - '0');
        i++;
        sb.insert(0, (ac + bc + j) % 2);
        j = (ac + bc + j) / 2;
      }
      if (j > 0)
        sb.insert(0, 1);
      return sb.toString();
    }
  }

}
