package practice;

/**
 *
 * <code>LeetCode Problem 62<code>
 * <p>
 * <li>A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram
 * below).
 * <li>The robot can only move either down or right at any point in time. The robot is trying to
 * reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <li>How many possible unique paths are there?
 * <li>Above is a 3 x 7 grid. How many possible unique paths are there?
 * <li>Note: m and n will be at most 100.
 * </p>
 * 
 */

public class LeetCode_62 {

  public static void main(String argv[]) {
    // System.out.println(new LeetCode_61().new Solution().rotateRight(3, 2));
  }

  /**
   * 一开始想的回溯法，一秒后发现DP也可以
   * 
   * 这道题用DP的方法很简单，用一个二维数组 paths[m][n] 存放到每个位置的path数
   * 
   * 那么paths[0][0]初始化为1
   * 
   * paths[i][j]等于paths[i-1][j] + paths[i][j-1]
   */
  class Solution {
    public int uniquePaths(int m, int n) {
      int[][] paths = new int[m][n];
      paths[0][0] = 1;
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++) {
          if (i != 0 || j != 0) {
            int left = j == 0 ? 0 : paths[i][j - 1];
            int up = i == 0 ? 0 : paths[i - 1][j];
            paths[i][j] = left + up;
          }
        }
      return paths[m - 1][n - 1];
    }
  }

}
