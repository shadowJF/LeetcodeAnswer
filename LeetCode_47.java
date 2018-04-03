package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 47<code>
 * <p>
 * <li>Given a collection of numbers that might contain duplicates, return all possible unique
 * permutations.
 * <li>For example,
 * <li>[1,1,2] have the following unique permutations:
 * <li>[
 * <li>[1,1,2],
 * <li>[1,2,1],
 * <li>[2,1,1]
 * <li>]
 * </p>
 * 
 */

public class LeetCode_47 {

  public static void main(String argv[]) {
    int[] tmp = {2, 3, 1, 1, 4};
    System.out.println(new LeetCode_47().new Solution().permuteUnique(tmp));
  }

  /**
   * 这题跟上一道差不多，稍微增加点难度
   * 
   * 我们需要一个额外的空间需求
   * 
   * 用一个map，存每个数，出现的次数
   * 
   * 然后再回溯的过程中，先判断这个数是不是已经被取完了，以及在这轮里面，是否已经被取过了
   * 
   * 如果是，则不能用该数字
   */
  class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
      List<List<Integer>> result = new ArrayList<List<Integer>>();
      List<Integer> one = new ArrayList<Integer>();
      HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
      for (int n : nums) {
        map.put(n, map.getOrDefault(n, 0) + 1);
      }

      permute(map, result, one, nums);
      return result;
    }

    private void permute(HashMap<Integer, Integer> map, List<List<Integer>> result,
        List<Integer> one, int[] nums) {
      if (one.size() == nums.length && one.size() != 0) {
        result.add(new ArrayList<Integer>(one));
        return;
      }

      ArrayList<Integer> has = new ArrayList<Integer>();
      for (int i = 0; i < nums.length; i++) {
        if (map.get(nums[i]).equals(0) || has.contains(nums[i]))
          continue;
        has.add(nums[i]);
        map.put(nums[i], map.get(nums[i]) - 1);
        one.add(nums[i]);
        permute(map, result, one, nums);
        one.remove(one.size() - 1);
        map.put(nums[i], map.get(nums[i]) + 1);
      }
    }
  }
}
