package practice;

/**
 *
 * <code>LeetCode Problem 7<code>
 * <p>
 * <li>Reverse digits of an integer.</li>
 * <li>Example1: x = 123, return 321</li>
 * <li>Example2: x = -123, return -321</li>
 * <li>click to show spoilers.</li>
 * <li>Note:</li>
 * <li>The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.</li>
 * </p>
 * 
 */

public class LeetCode_7 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_7().new Solution().reverse(-2147483648));
  }

  /**
   * 
   * 这题还是比较简单的，但是也是有很地方要考虑的，主要是考察你是不是考虑问题全面吧
   *
   * 第一，如果数字以0结尾怎么办
   *
   * 第二，因为数字是有符号整数，32位的，所以数字的标识范围为 -2147483648到2147483647之间，如果溢出了要返回0
   *
   * 所以可能出现，倒过来之前没有溢出，但是倒过来后溢出了的情况
   *
   * 第三，考虑边界情况，如输入是0、最小的复数-2147483648时
   */
  class Solution {
    public int reverse(int x) {
      boolean positive = x > 0 ? true : false;
      boolean ignoreZero = true;
      StringBuilder sb = new StringBuilder();
      if (!positive) {
        if (x == Integer.MIN_VALUE)
          return 0;
        x = -x;
      }
      while (x != 0) {
        int endNum = x % 10;
        if (endNum != 0 || !ignoreZero) {
          sb.append(endNum);
          if (ignoreZero)
            ignoreZero = false;
        }
        x = x / 10;
      }

      if (sb.toString().equals("")) {
        return 0;
      }

      Long l = Long.parseLong(sb.toString());
      if (!positive)
        l = -l;
      if (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE)
        return 0;

      int result = Integer.parseInt(sb.toString());
      if (!positive)
        result = -result;
      return result;

    }
  }
}
