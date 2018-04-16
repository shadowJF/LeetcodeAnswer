package practice;

/**
 *
 * <code>LeetCode Problem 63<code>
 * <p>
 * <li>Follow up for "Unique Paths":
 * <li>Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * <li>An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * <li>For example,
 * <li>There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * <li>[
 * <li>[0,0,0],
 * <li>[0,1,0],
 * <li>[0,0,0]
 * <li>]
 * <li>The total number of unique paths is 2.
 * <li>Note: m and n will be at most 100.
 * </p>
 * 
 */

public class LeetCode_63 {

  public static void main(String argv[]) {
    // System.out.println(new LeetCode_61().new Solution().rotateRight(3, 2));
  }

  /**
   * 这道题跟上一道思路一样，只不过要考虑初始节点为障碍的情形
   * 
   * 以及障碍物所在节点的path为0
   */
  class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
      int[][] paths = new int[obstacleGrid.length][obstacleGrid[0].length];
      paths[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
      for (int i = 0; i < obstacleGrid.length; i++)
        for (int j = 0; j < obstacleGrid[0].length; j++) {
          if ((i != 0 || j != 0) && obstacleGrid[i][j] == 0) {
            int left = j == 0 ? 0 : paths[i][j - 1];
            int up = i == 0 ? 0 : paths[i - 1][j];
            paths[i][j] = left + up;
          }
        }
      return paths[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }
  }

}
