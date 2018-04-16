package practice;

/**
 *
 * <code>LeetCode Problem 69<code>
 * <p>
 * <li>Implement int sqrt(int x).
 * <li>Compute and return the square root of x.
 * <li>x is guaranteed to be a non-negative integer.
 * <li>Example 1:
 * <li>Input: 4
 * <li>Output: 2
 * <li>Example 2:
 * <li>Input: 8
 * <li>Output: 2
 * <li>Explanation: The square root of 8 is 2.82842..., and since we want to return an integer, the
 * decimal part will be truncated.
 * </p>
 * 
 */

public class LeetCode_69 {

  public static void main(String argv[]) {
    // String[] tmp = {"Listen", "to", "many,", "speak", "to", "a", "few."};
    System.out.println(new LeetCode_69().new Solution().mySqrt(4));
  }

  /**
   * 这道题用二分法解，最小是0，最大是Integer.MAX_VALUE
   * 
   * 然后不断的取中间值
   * 
   * 如果中间值得平方正好等于x或者中间值得平方小于x，但是中间值+1的平方大于x，则返回中间值
   * 
   * 如果中间值的平方大于x，则令hi 等于middle-1
   * 
   * 如果中间值+1的平方小于等于x，则令lo 等于middle+1
   */
  class Solution {
    public int mySqrt(int x) {
      int lo = 0;
      int hi = Integer.MAX_VALUE;
      while (lo <= hi) {
        long middle = (lo + hi) / 2;
        if (middle * middle == x || (middle * middle < x && (middle + 1) * (middle + 1) > x))
          return (int) middle;
        if (middle * middle > x) {
          hi = (int) middle - 1;
        }
        if ((middle + 1) * (middle + 1) <= x) {
          lo = (int) middle + 1;
        }
      }
      return 0;
    }
  }

}
