package practice;

/**
 *
 * <code>LeetCode Problem 55<code>
 * <p>
 * <li>Given an array of non-negative integers, you are initially positioned at the first index of
 * the array.
 * <li>Each element in the array represents your maximum jump length at that position.
 * <li>Determine if you are able to reach the last index.
 * <li>For example:
 * <li>A = [2,3,1,1,4], return true.
 * <li>A = [3,2,1,0,4], return false.
 * </p>
 * 
 */

public class LeetCode_55 {

  public static void main(String argv[]) {
    int[] tmp = {2, 3};
    System.out.println(new LeetCode_55().new Solution().canJump(tmp));
  }

  class Solution {
    public boolean canJump(int[] nums) {
      int maxReach = 0;
      for (int i = 0; i < nums.length; i++) {
        if (i > maxReach)
          return false;
        int reach = i + nums[i];
        if (reach > maxReach)
          maxReach = reach;
        if (maxReach >= nums.length - 1)
          return true;
      }
      return true;
    }
  }
}
