package practice;

/**
 *
 * <code>LeetCode Problem 29<code>
 * <p>
 * <li>Divide two integers without using multiplication, division and mod operator.
 * <li>If it is overflow, return MAX_INT.
 * </p>
 * 
 */

public class LeetCode_29 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_29().new Solution().divide(2147483647, 3));
  }

  /**
   * 其实这道题不算难，但是因为Integer的范围问题，-2147483648 到 2147483647之间
   * 
   * 涉及到-2147483648时，如果用int处理容易不好搞
   * 
   * 所以看别人的算法都转成long型了再计算的 = = 我咋没想到。。
   * 
   * 我的算法还是用的int，不过涉及到边界值的处理，所以看起来比较屎
   * 
   * 我的思想是，每次将被除数除以2，然后只计算他的一半，除以除数之后的值和余数，
   * 
   * 那么另一半除以除数的结果应该是一样的
   * 
   * 但是需要注意到当被除数为奇数时，有一半是大1的，这个就需要额外考虑
   * 
   * 然后每次都这样分一半，直到达到边界条件
   * 
   * 这样就完成了一个递归处理的算法，每次都二分处理，时间复杂度显然为O(logn)
   * 
   * 最后放上别人转long型的解法，其实他的思路跟我最开始的思路一致
   * 
   * 每次都将divisor double一下，最后直到大于dividend，然后获取差值，再递归处理
   */
  class Solution {
    public int divide(int dividend, int divisor) {
      boolean positive = true;
      if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
        positive = false;

      int result;

      if (dividend == Integer.MIN_VALUE) {
        if (divisor == 1)
          return Integer.MIN_VALUE;
        if (divisor == -1)
          return Integer.MAX_VALUE; // 这里不知道为什么-2147483648 / -1 等于 2147483647
        if (divisor == Integer.MIN_VALUE)
          return 1;
        int[] r1 = div(Integer.MAX_VALUE, divisor);
        int[] r2 = div(1, divisor);
        int[] r3 = div(r1[1] + r2[1], divisor);
        result = r1[0] + r2[0] + r3[0];
      } else if (divisor == Integer.MIN_VALUE) {
        return 0;
      } else {
        result = div(dividend, divisor)[0];
      }

      if (positive)
        return result;
      else
        return 0 - result;
    }

    /**
     * 两个正数的除法
     */
    private int[] div(int dividend, int divisor) {
      dividend = Math.abs(dividend);
      divisor = Math.abs(divisor);
      int[] result = new int[2];
      if (divisor == 0) {
        result[0] = 0;
        result[1] = 0;
      } else if (divisor == 1) {
        result[0] = dividend;
        result[1] = 0;
      } else if (dividend < divisor) {
        result[0] = 0;
        result[1] = dividend;
      } else if (dividend == divisor) {
        result[0] = 1;
        result[1] = 0;
      } else {
        int half_dividend = dividend >> 1;
        int half_dividend2 = dividend - half_dividend;
        int[] r1 = div(half_dividend, divisor);
        int[] r2 = new int[2];
        r2[0] = r1[0];
        r2[1] = r1[1];
        // 如果dividend是奇数的情况
        if (half_dividend != half_dividend2) {
          r2[1] += 1;
          if (r2[1] == divisor) {
            r2[0] += 1;
            r2[1] = 0;
          }
        }
        if (r1[0] == 0 && r2[0] == 0) {
          result[0] = 1;
          result[1] = dividend - divisor;
        } else {
          int[] r3 = div(r1[1] + r2[1], divisor);
          result[0] = r1[0] + r2[0] + r3[0];
          result[1] = r3[1];
        }
      }

      return result;

    }

    /**
     * 别人家的解法
     */
    public int divide2(int dividend, int divisor) {
      // Reduce the problem to positive long integer to make it easier.
      // Use long to avoid integer overflow cases.
      int sign = 1;
      if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
        sign = -1;
      long ldividend = Math.abs((long) dividend);
      long ldivisor = Math.abs((long) divisor);

      // Take care the edge cases.
      if (ldivisor == 0)
        return Integer.MAX_VALUE;
      if ((ldividend == 0) || (ldividend < ldivisor))
        return 0;

      long lans = ldivide(ldividend, ldivisor);

      int ans;
      if (lans > Integer.MAX_VALUE) { // Handle overflow.
        ans = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      } else {
        ans = (int) (sign * lans);
      }
      return ans;
    }

    private long ldivide(long ldividend, long ldivisor) {
      // Recursion exit condition
      if (ldividend < ldivisor)
        return 0;

      // Find the largest multiple so that (divisor * multiple <= dividend),
      // whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
      // Think this as a binary search.
      long sum = ldivisor;
      long multiple = 1;
      while ((sum + sum) <= ldividend) {
        sum += sum;
        multiple += multiple;
      }
      // Look for additional value for the multiple from the reminder (dividend - sum) recursively.
      return multiple + ldivide(ldividend - sum, ldivisor);
    }
  }

}
