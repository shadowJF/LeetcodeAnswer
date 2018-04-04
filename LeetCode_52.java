package practice;

/**
 *
 * <code>LeetCode Problem 51<code>
 * <p>
 * <li>Follow up for N-Queens problem.
 * <li>Now, instead outputting board configurations, return the total number of distinct solutions.
 * </p>
 * 
 */

public class LeetCode_52 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_52().new Solution().totalNQueens(2));
  }

  /**
   * 这道题跟上道题一样，因为只需要知道不同解的个数，我们只要在初始化place数组时，多申请一个位置，用来存不同解的个数即可
   */
  class Solution {
    public int totalNQueens(int n) {
      int[] place = new int[n + 1]; // 记录每一行皇后的位置,同时用最后一个位置记录找到了多少种可能
      backTracking(place, 0);
      return place[n];
    }

    private void backTracking(int[] place, int index) {
      int n = place.length - 1;
      if (index == n) {
        place[n] += 1;
        return;
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
          backTracking(place, index + 1);
        }
      }
    }
  }
}
