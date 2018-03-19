package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 39<code>
 * <p>
 * <li>Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all
 * unique combinations in C where the candidate numbers sums to T.
 * <li>The same repeated number may be chosen from C unlimited number of times.
 * <li>Note:
 * <li>All numbers (including target) will be positive integers.
 * <li>The solution set must not contain duplicate combinations.
 * <li>For example, given candidate set [2, 3, 6, 7] and target 7,
 * <li>A solution set is:
 * <li>[
 * <li>[7],
 * <li>[2, 2, 3]
 * <li>]
 * </p>
 * 
 */

public class LeetCode_39 {

  public static void main(String argv[]) {
    int[] tmp = {1, 2};
    List<List<Integer>> result = new LeetCode_39().new Solution().combinationSum(tmp, 1);

    System.out.println(result);
  }

  /**
   * 这道题也是用递归来解，每次从尾到头选择一个元素，然后用target减去该元素后得到nexttarget，然后递归求解nexttarget的集合
   * 
   * 如果能得到，就将该元素添加到集合中每一个数组中，得到target的结果
   * 
   * 需要注意的是，因为不能找重复的结果，所以我们每次找的时候，都要设置一个上限，每次找元素的数组上限都是上一个找到的元素位置。
   * 
   * 就比如[2,3]，target为7
   * 
   * 我从尾向头开始遍历，先找到3，用7-3,得到4，那么再从数组中找到能相加得到4的，最后得到2,2,3
   * 
   * 那么3的处理完了，我再处理2的，7-2得到5，我如果还是从整个数组中找能相加得到5的，最后也是得到2,2,3,
   * 
   * 那么就重复了，所以在处理2开头时，就应该是从数组的上限为0，也就是[2]中能相加得到5的，因为2后面的我其实能找到的都在之前的元素处理时获得了
   * 
   * 
   */
  class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
      Arrays.sort(candidates);
      List<List<Integer>> result = recursion(candidates, target, candidates.length - 1);
      return result == null ? new ArrayList<List<Integer>>() : result;
    }

    private List<List<Integer>> recursion(int[] candidates, int target, int hi) {
      List<List<Integer>> result = new ArrayList<List<Integer>>();

      if (target == 0) {
        result.add(new ArrayList<Integer>());
        return result;
      }

      for (int i = hi; i >= 0; i--) {
        if (candidates[i] > target) {
          continue;
        } else {
          int nextTarget = target - candidates[i];
          List<List<Integer>> result2 = recursion(candidates, nextTarget, i);
          if (result2 != null) {
            for (int j = 0; j < result2.size(); j++)
              result2.get(j).add(candidates[i]);
            result.addAll(result2);
          }
        }
      }

      if (result.isEmpty())
        return null;
      else
        return result;

    }
  }
}
