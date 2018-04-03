package practice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 46<code>
 * <p>
 * <li>Given a collection of distinct numbers, return all possible permutations.
 * <li>For example,
 * <li>[1,2,3] have the following permutations:
 * <li>[
 * <li>[1,2,3],
 * <li>[1,3,2],
 * <li>[2,1,3],
 * <li>[2,3,1],
 * <li>[3,1,2],
 * <li>[3,2,1]
 * <li>]
 * </p>
 * 
 */

public class LeetCode_46 {

  public static void main(String argv[]) {
    int[] tmp = {2, 3, 1, 1, 4};
    System.out.println(new LeetCode_46().new Solution().permute(tmp));
  }

  class Solution {
    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> result = new ArrayList<List<Integer>>();
      List<Integer> one = new ArrayList<Integer>();

      permute(result, one, nums);
      return result;
    }

    private void permute(List<List<Integer>> result, List<Integer> one, int[] nums) {
      if (one.size() == nums.length && one.size() != 0) {
        result.add(new ArrayList<Integer>(one));
        return;
      }
      for (int i = 0; i < nums.length; i++) {
        if (one.contains(nums[i]))
          continue;
        one.add(nums[i]);
        permute(result, one, nums);
        one.remove(one.size() - 1);
      }
    }
  }
}
