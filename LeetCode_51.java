package practice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 51<code>
 * <p>
 * <li>The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two
 * queens attack each other.
 * <li>Given an integer n, return all distinct solutions to the n-queens puzzle.
 * <li>Each solution contains a distinct board configuration of the n-queens' placement, where 'Q'
 * and '.' both indicate a queen and an empty space respectively.
 * <li>For example,
 * <li>There exist two distinct solutions to the 4-queens puzzle:
 * <li>[
 * <li>[".Q..", // Solution 1
 * <li>"...Q",
 * <li>"Q...",
 * <li>"..Q."],
 * <li>["..Q.", // Solution 2
 * <li>"Q...",
 * <li>"...Q",
 * <li>".Q.."]
 * <li>]
 * </p>
 * 
 */

public class LeetCode_51 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_51().new Solution().solveNQueens(2));
  }

  /**
   * 八皇后问题，典型的回溯法 解决问题
   * 
   * 首先说下啥是八皇后问题
   * 
   * 就是两个皇后不能在同一行同一列同一斜线上
   * 
   * 那么我们就知道，在一个n*n的棋盘上，每一行肯定只能放一个皇后
   * 
   * 这样，我用一个数组place[n]记录每一行放的皇后的位置
   * 
   * 再然后用回溯法，去递归处理每一行
   * 
   * 在每一行选择位置时，只要保证他和前面行选出来的皇后不会互相攻击即可
   * 
   * 如何保证不会互相攻击呢？
   * 
   * 只要行互不相同、列互不相同、行的差与列的差的绝对值不相等，就能保证了
   */
  class Solution {
    public List<List<String>> solveNQueens(int n) {
      int[] place = new int[n]; // 记录每一行皇后的位置
      List<List<String>> result = new ArrayList<List<String>>();
      backTracking(result, place, 0);
      return result;
    }

    private void backTracking(List<List<String>> result, int[] place, int index) {
      int n = place.length;
      if (index == n) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
          StringBuilder sb = new StringBuilder();
          for (int j = 1; j < place[i]; j++)
            sb.append('.');
          sb.append("Q");
          for (int j = place[i] + 1; j <= n; j++)
            sb.append('.');
          list.add(sb.toString());
        }
        result.add(list);
      }

      for (int i = 1; i <= n; i++) { // 遍历这一行皇后可能取的位置
        boolean flag = true; // 代表是否可以放在该位置
        for (int j = 0; j < index; j++) {
          if ((place[j] == i) || (Math.abs(index - j) == Math.abs(i - place[j]))) {
            flag = false;
            break;
          }
        }
        if (flag) {
          place[index] = i;
          backTracking(result, place, index + 1);
        }
      }
    }
  }
}
