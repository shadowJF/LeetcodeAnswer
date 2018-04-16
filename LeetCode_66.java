package practice;

/**
 *
 * <code>LeetCode Problem 66<code>
 * <p>
 * <li>Given a non-negative integer represented as a non-empty array of digits, plus one to the
 * integer.
 * <li>You may assume the integer do not contain any leading zero, except the number 0 itself.
 * <li>The digits are stored such that the most significant digit is at the head of the list.
 * </p>
 * 
 */

public class LeetCode_66 {

  public static void main(String argv[]) {
    // int[][] tmp = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
    // System.out.println(new LeetCode_66().new Solution().plusOne("+.8"));
  }

  /**
   * 这道题虽然简单，但是要写出一遍循环的巧妙解法还是有难度的
   * 
   * 我的解法就很蠢了，循环两遍，就不说了
   * 
   * 巧妙的解法是从尾开始向前遍历数组
   * 
   * 遇到小于9的，直接加1后返回原数组就行了
   * 
   * 如果遇到等于9的，则让他等于0
   * 
   * 循环之中如果返回了，那么就说明数字+1后还是原来的长度
   * 
   * 如果循环退出之后还没返回，则new一个新的数组，长度为原数组的长度+1
   * 
   * 并且令新数组第一个数为1即可
   */
  class Solution {
    public int[] plusOne(int[] digits) {
      int nineCount = 0;
      for (int i = 0; i < digits.length; i++) {
        if (digits[i] == 9)
          nineCount++;
      }
      if (nineCount == digits.length) {
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
      } else {
        int[] result = new int[digits.length];
        int j = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
          result[result.length - (digits.length - i)] = (digits[i] + j) % 10;
          j = (digits[i] + j) / 10;
        }
        return result;
      }
    }
  }

}
