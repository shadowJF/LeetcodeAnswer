package practice;

/**
 *
 * <code>LeetCode Problem 50<code>
 * <p>
 * <li>Implement pow(x, n).
 * <li>Example 1:
 * <li>Input: 2.00000, 10
 * <li>Output: 1024.00000
 * <li>Example 2:
 * <li>Input: 2.10000, 3
 * <li>Output: 9.26100
 * </p>
 * 
 */

public class LeetCode_50 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_50().new Solution().myPow(2.00000, -2147483648));
  }

  /**
   * 这题一开始想的也很简单
   * 
   * 就一直for循环 不断乘以x不就行了
   * 
   * 但是当n很大时，就超时了
   * 
   * 所以还是得采用log的思想
   * 
   * 每次计算x的1,2,4,8...次方
   * 
   * 这样就是logn的时间
   * 
   * 需要注意的是，n是int型，为了计算的时候，例如取绝对值，还有i*2不溢出，将他先转化成long型再处理
   */
  class Solution {
    public double myPow(double x, int n) {
      if (n == 0)
        return 1;
      double result = 1;
      long count = Math.abs((long) (n));
      while (count >= 1) {
        double curR = x;
        long i = 2;
        for (; i <= count; i *= 2)
          curR *= curR;
        result *= curR;
        count = count - i / 2;
      }
      return n > 0 ? result : 1 / result;
    }
  }
}
