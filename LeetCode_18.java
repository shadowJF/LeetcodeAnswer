package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 18<code>
 * <p>
 * <li>Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c +
 * d = target? Find all unique quadruplets in the array which gives the sum of target.
 * <li>Note: The solution set must not contain duplicate quadruplets.
 * <li>For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 * <li>A solution set is:
 * <li>[
 * <li>[-1, 0, 0, 1],
 * <li>[-2, -1, 1, 2],
 * <li>[-2, 0, 0, 2]
 * <li>]
 * </p>
 * 
 */

public class LeetCode_18 {

  public static void main(String argv[]) {
    int[] a = {-1, 0, -5, -2, -2, -4, 0, 1, -2};

    System.out.println(new LeetCode_18().new Solution().fourSum(a, -9));
  }

  class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
      Arrays.sort(nums);
      List<List<Integer>> result = new ArrayList<List<Integer>>();

      if (nums.length < 4)
        return result;

      if (nums[0] + nums[1] + nums[2] + nums[3] > target)
        return result;
      if (nums[nums.length - 4] + nums[nums.length - 1] + nums[nums.length - 2]
          + nums[nums.length - 3] < target)
        return result;

      for (int i = 0; i < nums.length - 3; i++) {
        if (i > 0 && nums[i] == nums[i - 1])
          continue;
        if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target)
          break;
        if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2]
            + nums[nums.length - 3] < target)
          continue;

        int target2 = target - nums[i];

        for (int j = i + 1; j < nums.length - 2; j++) {
          if (j > i + 1 && nums[j] == nums[j - 1])
            continue;
          if (nums[j] + nums[j + 1] + nums[j + 2] > target2)
            break;
          if (nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target2)
            continue;

          int lo = j + 1;
          int hi = nums.length - 1;
          while (lo < hi) {
            if (nums[lo] + nums[hi] == target2 - nums[j]) {
              result.add(this.getList(nums[i], nums[j], nums[lo], nums[hi]));
              while (lo < hi && nums[lo] == nums[lo + 1])
                lo++;
              while (lo < hi && nums[hi] == nums[hi - 1])
                hi--;
              lo++;
              hi--;
            } else if (nums[lo] + nums[hi] < target2 - nums[j]) {
              lo++;
            } else {
              hi--;
            }
          }
        }
      }

      return result;
    }

    private List<Integer> getList(int a, int b, int c, int d) {
      List<Integer> l = new ArrayList<Integer>();
      l.add(a);
      l.add(b);
      l.add(c);
      l.add(d);
      return l;
    }
  }
}
