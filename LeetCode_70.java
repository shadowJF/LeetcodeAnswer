package practice;

/**
 *
 * <code>LeetCode Problem 70<code>
 * <p>
 * <li>You are climbing a stair case. It takes n steps to reach to the top.
 * <li>Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the
 * top?
 * <li>Note: Given n will be a positive integer.
 * <li>Example 1:
 * <li>Input: 2
 * <li>Output: 2
 * <li>Explanation: There are two ways to climb to the top.
 * <li>1. 1 step + 1 step
 * <li>2. 2 steps
 * <li>Example 2:
 * <li>Input: 3
 * <li>Output: 3
 * <li>Explanation: There are three ways to climb to the top.
 * <li>1. 1 step + 1 step + 1 step
 * <li>2. 1 step + 2 steps
 * <li>3. 2 steps + 1 step
 * </p>
 * 
 */

public class LeetCode_70 {

  public static void main(String argv[]) {
    // // String[] tmp = {"Listen", "to", "many,", "speak", "to", "a", "few."};
    // System.out.println(new LeetCode_70().new Solution().mySqrt(4));
  }

  /**
   * DP,不用多说
   */
  class Solution {
    public int climbStairs(int n) {
      int[] distinct = new int[n + 1];
      distinct[0] = 1;
      distinct[1] = 1;
      for (int i = 2; i <= n; i++) {
        distinct[i] = distinct[i - 1] + distinct[i - 2];
      }
      return distinct[n];
    }
  }

}
