package practice;

/**
 *
 * <code>LeetCode Problem 74<code>
 * <p>
 * <li>Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has
 * the following properties:
 * <li>Integers in each row are sorted from left to right.
 * <li>The first integer of each row is greater than the last integer of the previous row.
 * <li>Example 1:
 * <li>Input:
 * <li>matrix = [
 * <li>[1, 3, 5, 7],
 * <li>[10, 11, 16, 20],
 * <li>[23, 30, 34, 50]
 * <li>]
 * <li>target = 3
 * <li>Output: true
 * <li>Example 2:
 * <li>Input:
 * <li>matrix = [
 * <li>[1, 3, 5, 7],
 * <li>[10, 11, 16, 20],
 * <li>[23, 30, 34, 50]
 * <li>]
 * <li>target = 13
 * <li>Output: false
 * </p>
 * 
 */

public class LeetCode_74 {

  public static void main(String argv[]) {
    // // String[] tmp = {"Listen", "to", "many,", "speak", "to", "a", "few."};
    // System.out.println(new LeetCode_73().new Solution().setZeroes("/home/foo/./bar/", ""));
  }

  /**
   * 这道题还是很简单的，不要把他当做一个二维数组，当做一个有序数组，那么这个问题就是
   * 
   * 二分查找能解决的
   * 
   * 我们唯一要多做一步的就是将序列号转换为矩阵中的行列值
   */
  class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
      int total = matrix.length == 0 ? 0 : matrix.length * matrix[0].length;
      int lo = 1;
      int hi = total;
      while (lo <= hi) {
        int middle = (lo + hi) / 2;
        int i = middle % matrix[0].length == 0 ? middle / matrix[0].length - 1
            : middle / matrix[0].length;
        int j =
            middle % matrix[0].length == 0 ? matrix[0].length - 1 : middle % matrix[0].length - 1;
        if (matrix[i][j] == target)
          return true;
        if (matrix[i][j] > target)
          hi = middle - 1;
        else
          lo = middle + 1;
      }
      return false;
    }
  }

}
