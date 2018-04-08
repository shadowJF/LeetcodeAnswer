package practice;

/**
 *
 * <code>LeetCode Problem 58<code>
 * <p>
 * <li>Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral
 * order.
 * <li>For example,
 * <li>Given n = 3,
 * <li>You should return the following matrix:
 * <li>[
 * <li>[ 1, 2, 3 ],
 * <li>[ 8, 9, 4 ],
 * <li>[ 7, 6, 5 ]
 * <li>]
 * </p>
 * 
 */

public class LeetCode_59 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_59().new Solution().generateMatrix(5));
  }

  /**
   * 这道题跟上一到螺旋遍历的题目解题思路一模一样
   * 
   * 就不详解了
   */
  class Solution {
    public int[][] generateMatrix(int n) {
      int[][] matrix = new int[n][n];

      int[] bounds = new int[4];
      bounds[0] = n - 1;
      bounds[1] = n - 1;
      bounds[2] = 0;
      bounds[3] = 0;

      int index_i = 0, index_j = 0;
      int direct = 0;
      int fillNum = 0;

      while (fillNum < n * n) {
        if (direct == 0) {
          for (int i = index_j; i <= bounds[0]; i++)
            matrix[index_i][i] = ++fillNum;
          index_j = bounds[0];
          index_i++;
          bounds[3]++;
        } else if (direct == 1) {
          for (int i = index_i; i <= bounds[1]; i++)
            matrix[i][index_j] = ++fillNum;
          index_j--;
          index_i = bounds[1];
          bounds[0]--;
        } else if (direct == 2) {
          for (int i = index_j; i >= bounds[2]; i--)
            matrix[index_i][i] = ++fillNum;
          index_i--;
          index_j = bounds[2];
          bounds[1]--;
        } else {
          for (int i = index_i; i >= bounds[3]; i--)
            matrix[i][index_j] = ++fillNum;
          index_j++;
          index_i = bounds[3];
          bounds[2]++;
        }
        direct = (direct + 1) % 4;
      }
      return matrix;
    }
  }

}
