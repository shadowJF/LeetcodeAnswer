package practice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 77<code>
 * <p>
 * <li>Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * <li>Example:
 * <li>Input: n = 4, k = 2
 * <li>Output：
 * <li>[
 * <li>[2,4],
 * <li>[3,4],
 * <li>[2,3],
 * <li>[1,2],
 * <li>[1,3],
 * <li>[1,4],
 * <li>]
 * </p>
 * 
 */

public class LeetCode_77 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_77().new Solution().combine(4, 2));
  }

  /**
   * 标准的递归回溯解法，不多说
   */
  class Solution {
    public List<List<Integer>> combine(int n, int k) {
      if (k > n)
        return null;
      List<List<Integer>> result = new ArrayList<List<Integer>>();
      backtracking(result, new ArrayList<Integer>(), 1, n, k);
      return result;

    }

    private void backtracking(List<List<Integer>> result, List<Integer> list, int start, int n,
        int k) {
      if (list.size() == k) {
        result.add(new ArrayList<Integer>(list));
        return;
      }
      for (int i = start; i <= n; i++) {
        if (n - i + 1 < k - list.size())
          break;
        list.add(i);
        backtracking(result, list, i + 1, n, k);
        list.remove(list.size() - 1);
      }
    }
  }

}
