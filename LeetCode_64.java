package practice;

/**
 *
 * <code>LeetCode Problem 64<code>
 * <p>
 * <li>Given a m x n grid filled with non-negative numbers, find a path from top left to bottom
 * right which minimizes the sum of all numbers along its path.
 * <li>Note: You can only move either down or right at any point in time.
 * <li>Example 1:
 * <li>[[1,3,1],
 * <li>[1,5,1],
 * <li>[4,2,1]]
 * <li>Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
 * </p>
 * 
 */

public class LeetCode_64 {

  public static void main(String argv[]) {
    int[][] tmp = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
    System.out.println(new LeetCode_64().new Solution().minPathSum(tmp));
  }

  /**
   * DP解决和上面两个问题类似
   */
  class Solution {
    public int minPathSum(int[][] grid) {
      int[][] sum = new int[grid.length][grid[0].length];
      sum[0][0] = grid[0][0];
      for (int i = 0; i < grid.length; i++)
        for (int j = 0; j < grid[0].length; j++) {
          if (j != 0 && i == 0)
            sum[i][j] = sum[i][j - 1] + grid[i][j];
          if (i != 0 && j == 0)
            sum[i][j] = sum[i - 1][j] + grid[i][j];
          if (i != 0 && j != 0)
            sum[i][j] =
                grid[i][j] + (sum[i - 1][j] > sum[i][j - 1] ? sum[i][j - 1] : sum[i - 1][j]);
        }
      return sum[grid.length - 1][grid[0].length - 1];
    }
  }

}
