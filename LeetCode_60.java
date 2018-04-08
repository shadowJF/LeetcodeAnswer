package practice;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * <code>LeetCode Problem 60<code>
 * <p>
 * <li>The set [1,2,3,…,n] contains a total of n! unique permutations.
 * <li>By listing and labeling all of the permutations in order,
 * <li>We get the following sequence (ie, for n = 3):
 * <li>"123"
 * <li>"132"
 * <li>"213"
 * <li>"231"
 * <li>"312"
 * <li>"321"
 * <li>Given n and k, return the kth permutation sequence.
 * <li>Note: Given n will be between 1 and 9 inclusive.
 * </p>
 * 
 */

public class LeetCode_60 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_60().new Solution().getPermutation(3, 2));
  }

  /**
   * 这道题当然可以用回溯法去解，不断递归，直到取到第k个为止
   * 
   * 然而我这里想了一个不用递归的方法
   * 
   * 我们每次求解第i位的数字
   * 
   * 为了直到第i位的数字是多少，我们得知道，i位之后还有n-i个位置
   * 
   * 这n-i个位置的可能排列方式有n-i的阶乘种
   * 
   * 那么我用k除以n-i的阶乘，等于y，余数为k，如果能整除，那么就说明第i位为剩下能取的第y个数，同时，后面的排列就固定了，是最大的排列方式
   * 
   * 如果不能整除，那么说明第i位是剩下能取的第y+1个数，同时，更新k为余数，然后计算第i+1个位置的取值
   */
  class Solution {
    public String getPermutation(int n, int k) {
      Set<Integer> set = new HashSet<Integer>();
      StringBuilder sb = new StringBuilder();
      for (int i = 1; i <= n; i++) {
        if (k == 0)
          sb.append(this.getMaxNumber(n, set));
        else {
          int y = k / this.getNFactorial(n - i);
          k = k % this.getNFactorial(n - i);
          if (k != 0)
            sb.append(this.getNthNumber(n, y + 1, set));
          else {
            sb.append(this.getNthNumber(n, y, set));
          }
        }
      }
      return sb.toString();
    }

    public int getMaxNumber(int n, Set<Integer> set) {
      for (int i = n; i >= 1; i--) {
        if (!set.contains(i)) {
          set.add(i);
          return i;
        }
      }
      return 0;
    }

    public Integer getNthNumber(int n, int i, Set<Integer> set) {
      int count = 0;
      int result = 0;
      for (int k = 1; k <= n; k++) {
        if (!set.contains(k)) {
          count++;
          if (count == i) {
            set.add(k);
            result = k;
            break;
          }
        }
      }
      return result;
    }

    public int getNFactorial(int n) {
      if (n == 0) {
        return 1;
      }
      return n * getNFactorial(n - 1);
    }
  }

}
