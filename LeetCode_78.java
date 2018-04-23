package practice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 78<code>
 * <p>
 * <li>Given a set of distinct integers, nums, return all possible subsets (the power set).
 * <li>Note: The solution set must not contain duplicate subsets.
 * <li>Example:
 * <li>Input: nums = [1,2,3]
 * <li>Output:
 * <li>[
 * <li>[3],
 * <li>[1],
 * <li>[2],
 * <li>[1,2,3],
 * <li>[1,3],
 * <li>[2,3],
 * <li>[1,2],
 * <li>[]
 * <li>]
 * </p>
 * 
 */

public class LeetCode_78 {

  public static void main(String argv[]) {
    int[] tmp = {1, 2, 3};
    System.out.println(new LeetCode_78().new Solution().subsets(tmp));
  }

  /**
   * 还是跟上一题一样
   * 
   * 换汤不换药
   * 
   * 回溯 递归解决，不过需要两个指针，一个指定当前从哪个位置开始向后选择元素，一个指定当前遍历到第几层（最多只能遍历到最后一个元素）
   */
  class Solution {
    public List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> result = new ArrayList<List<Integer>>();
      result.add(new ArrayList<Integer>());
      backtracking(result, new ArrayList<Integer>(), nums, 0, 0);
      return result;
    }

    private void backtracking(List<List<Integer>> result, List<Integer> list, int[] nums, int index,
        int level) {
      if (level >= nums.length || index >= nums.length)
        return;
      for (int i = index; i < nums.length; i++) {
        list.add(nums[i]);
        result.add(new ArrayList<Integer>(list));
        backtracking(result, list, nums, i + 1, level + 1);
        list.remove(list.size() - 1);
      }
    }
  }

}
