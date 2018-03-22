package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 40<code>
 * <p>
 * <li>Given a collection of candidate numbers (C) and a target number (T), find all unique
 * combinations in C where the candidate numbers sums to T.
 * <li>Each number in C may only be used once in the combination.
 * <li>Note:
 * <li>All numbers (including target) will be positive integers.
 * <li>The solution set must not contain duplicate combinations.
 * <li>For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 * <li>A solution set is:
 * <li>[
 * <li>[1, 7],
 * <li>[1, 2, 5],
 * <li>[2, 6],
 * <li>[1, 1, 6]
 * <li>]
 * </p>
 * 
 */

public class LeetCode_40 {

  public static void main(String argv[]) {
    int[] tmp = {1, 2, 3, 5};
    List<List<Integer>> result = new LeetCode_40().new Solution().combinationSum2(tmp, 5);

    System.out.println(result);
  }

  /**
   * 这道题跟上一道题差不多
   * 
   * 只不过这次，数组中的每个元素最多只能使用一次，而且数组中的元素有可能有重复
   * 
   * 还是用递归的算法， 回溯的思路
   * 
   * 只不过在回溯中，取某一个元素时，需要过滤掉相同的后面的元素，不然有可能出现重复
   */
  class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      List<List<Integer>> result = new ArrayList<List<Integer>>();
      Arrays.sort(candidates);
      backtracking(result, new ArrayList<Integer>(), candidates, target, 0);
      return result;
    }

    private void backtracking(List<List<Integer>> result, List<Integer> set, int[] candidates,
        int target, int index) {
      if (target == 0) {
        result.add(new ArrayList<Integer>(set));
      } else
        for (int i = index; i < candidates.length; i++) {
          if (i > index && candidates[i - 1] == candidates[i]) // 过滤掉相同的元素
            continue;
          if (candidates[i] <= target) {
            set.add(candidates[i]);
            backtracking(result, set, candidates, target - candidates[i], i + 1);
            set.remove(set.size() - 1);
          } else
            break;
        }
    }
  }
}
