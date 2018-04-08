package practice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 54<code>
 * <p>
 * <li>Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in
 * spiral order.
 * <li>For example,
 * <li>Given the following matrix:
 * <li>[
 * <li>[ 1, 2, 3 ],
 * <li>[ 4, 5, 6 ],
 * <li>[ 7, 8, 9 ]
 * <li>]
 * <li>You should return [1,2,3,6,9,8,7,4,5].
 * </p>
 * 
 */

public class LeetCode_54 {

  public static void main(String argv[]) {
    int[][] tmp = {{2, 3}};
    System.out.println(new LeetCode_54().new Solution().spiralOrder(tmp));
  }

  /**
   * 这道题，我是用一个数组bounds来存放四周的边界值
   * 
   * 并且用一个direct数据，来表示每次遍历的方向
   * 
   * 并且用一个index_i 和index_j 来表示当前遍历到的元素位置
   * 
   * 这样，当direct == 0时，就从左向右开始遍历，遍历到bounds[0]为止
   * 
   * 遍历完，记得更新index_i,index_j和bounds[3];
   * 
   * 当direct == 1时，就从上向下遍历，遍历到bounds[1]为止
   * 
   * 当direct == 2时，就从右向左遍历，遍历到bounds[2]为止
   * 
   * 当direct == 3时，就从下向上遍历，遍历到bounds[3]为止
   */
  class Solution {
    public List<Integer> spiralOrder2(int[][] matrix) {
      List<Integer> result = new ArrayList<Integer>();
      if (matrix == null || matrix.length == 0)
        return result;
      int[] bounds = new int[4];
      bounds[0] = matrix[0].length - 1;
      bounds[1] = matrix.length - 1;
      bounds[2] = 0;
      bounds[3] = 0;

      int index_i = 0, index_j = 0;
      int direct = 0;

      while (result.size() < matrix.length * matrix[0].length) {
        if (direct == 0) {
          for (int i = index_j; i <= bounds[0]; i++)
            result.add(matrix[index_i][i]);
          index_j = bounds[0];
          index_i++;
          bounds[3]++;
        } else if (direct == 1) {
          for (int i = index_i; i <= bounds[1]; i++)
            result.add(matrix[i][index_j]);
          index_j--;
          index_i = bounds[1];
          bounds[0]--;
        } else if (direct == 2) {
          for (int i = index_j; i >= bounds[2]; i--)
            result.add(matrix[index_i][i]);
          index_i--;
          index_j = bounds[2];
          bounds[1]--;
        } else {
          for (int i = index_i; i >= bounds[3]; i--)
            result.add(matrix[i][index_j]);
          index_j++;
          index_i = bounds[3];
          bounds[2]++;
        }
        direct = (direct + 1) % 4;
      }


      return result;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
      List<Integer> result = new ArrayList<Integer>();
      if (matrix == null || matrix.length == 0)
        return result;
      int[] bounds = new int[4];
      bounds[0] = matrix[0].length - 1;
      bounds[1] = matrix.length - 1;
      bounds[2] = 0;
      bounds[3] = 0;

      int index_i = 0, index_j = -1;

      while (bounds[0] >= bounds[2] && bounds[1] >= bounds[3]) {
        boolean flag = false;
        for (int i = index_j + 1; i <= bounds[0]; i++) {
          result.add(matrix[index_i][i]);
          flag = true;
        }
        if (!flag)
          break;
        index_j = bounds[0];
        bounds[3] += 1;
        flag = false;

        for (int i = index_i + 1; i <= bounds[1]; i++) {
          result.add(matrix[i][index_j]);
          flag = true;
        }
        if (!flag)
          break;
        index_i = bounds[1];
        bounds[0] -= 1;
        flag = false;

        for (int i = index_j - 1; i >= bounds[2]; i--) {
          result.add(matrix[index_i][i]);
          flag = true;
        }
        if (!flag)
          break;
        index_j = bounds[2];
        bounds[1] -= 1;
        flag = false;

        for (int i = index_i - 1; i >= bounds[3]; i--) {
          result.add(matrix[i][index_j]);
          flag = true;
        }
        if (!flag)
          break;
        index_i = bounds[3];
        bounds[2] += 1;
        flag = false;
      }
      return result;
    }
  }
}
