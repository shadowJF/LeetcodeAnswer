package practice;

/**
 *
 * <code>LeetCode Problem 47<code>
 * <p>
 * <li>You are given an n x n 2D matrix representing an image.
 * <li>Rotate the image by 90 degrees (clockwise).
 * <li>Note:
 * <li>You have to rotate the image in-place, which means you have to modify the input 2D matrix
 * directly. DO NOT allocate another 2D matrix and do the rotation.
 * <li>Example 1:
 * <li>Given input matrix =
 * <li>[
 * <li>[1,2,3],
 * <li>[4,5,6],
 * <li>[7,8,9]
 * <li>],
 * <li>rotate the input matrix in-place such that it becomes:
 * <li>[
 * <li>[7,4,1],
 * <li>[8,5,2],
 * <li>[9,6,3]
 * <li>]
 * <li>Example 2:
 * <li>Given input matrix =
 * <li>[
 * <li>[ 5, 1, 9,11],
 * <li>[ 2, 4, 8,10],
 * <li>[13, 3, 6, 7],
 * <li>[15,14,12,16]
 * <li>],
 * <li>rotate the input matrix in-place such that it becomes:
 * <li>[
 * <li>[15,13, 2, 5],
 * <li>[14, 3, 4, 1],
 * <li>[12, 6, 8, 9],
 * <li>[16, 7,10,11]
 * <li>]
 * </p>
 * 
 */

public class LeetCode_48 {

  public static void main(String argv[]) {
    int[][] tmp = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    new LeetCode_48().new Solution().rotate(tmp);
  }

  /**
   * 这题的时间复杂度是O(n)
   * 
   * 首先看到这道题，我们得先找规律
   * 
   * 假设一个4*4的矩阵
   * 
   * 0,0 -> 0,3 0,1 -> 1,3 0,2 -> 2,3 0,3 -> 3,3
   * 
   * 1,0 -> 0,2 1,1 -> 1,2 1,2 -> 2,2 1,3 -> 3,2
   * 
   * ...
   * 
   * 这样我们其实不难发现，坐标为i，j的数会换到坐标为j,n-i的地方，n为数组长度-1
   * 
   * 那么知道变换规则后，我们怎么遍历数据来转换呢
   * 
   * 我们可以一圈一圈地来处理，最开始我们处理最外面的圈
   * 
   * 那么从0,0开始，0,0换到0,3
   * 
   * 然后0,3 换到 3,3
   * 
   * 然后3,3 换到 3， 0
   * 
   * 然后3， 0 换到 0,0
   * 
   * 可以发现，每个数，转4次，这个数的转换就完成了
   * 
   * 然后再从0,1开始，换一圈
   * 
   * 直到0,2这一圈也换完
   * 
   * 那么最外面这圈就处理完了
   * 
   * 然后再从1,1开始处理第二圈
   * 
   * 直到最里面的圈，也就是从n/2，n/2开始的圈处理完就可以了
   */
  class Solution {
    public void rotate(int[][] matrix) {
      int n = matrix.length - 1;
      for (int i = 0; i <= n / 2; i++) {
        for (int j = i; j <= n - i - 1; j++) {
          int tmpi = i, tmpj = j;
          int tmp = matrix[tmpi][tmpj];
          for (int k = 0; k <= 3; k++) {
            int t = matrix[tmpj][n - tmpi];
            matrix[tmpj][n - tmpi] = tmp;
            tmp = t;
            int ti = tmpi;
            tmpi = tmpj;
            tmpj = n - ti;
          }
        }
      }
    }
  }
}
