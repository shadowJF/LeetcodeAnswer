package practice;

import java.util.Arrays;

/**
 *
 * <code>LeetCode Problem 16<code>
 * <p>
 * <li>Given an array S of n integers, find three integers in S such that the sum is closest to a
 * given number, target. Return the sum of the three integers. You may assume that each input would
 * have exactly one solution.
 * <li>For example, given array S = {-1 2 1 -4}, and target = 1.
 * <li>The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * </p>
 * 
 */

public class LeetCode_16 {

  public static void main(String argv[]) {
    int[] nums = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
    System.out.println(new LeetCode_16().new Solution().threeSumClosest(nums, 18));
  }

  /**
   * 这一题跟上一题思路一致
   * 
   * 首先对数组进行排序
   * 
   * 然后，从头开始遍历数组，去每一个元素作为三元组的第一个数
   * 
   * 然后设置两个游标，一个指向下一个数，一个指向数组尾部
   * 
   * 接着将两个游标相加，用target-第一个数的结果减去两个游标的和，得到他们之间的差值
   * 
   * 并且对差值取绝对值，和当前最小差值比较，如果更小，则更新最小差值
   * 
   * 然后根据差值的大小判断游标的走向
   * 
   * 如果差值大于0，则说明，两个游标相加的和小于target-第一个数
   * 
   * 所以需要将小的那一个游标向后移动，来增大他们的和，所以头部游标向后移动一位
   * 
   * 如果差值小于0，则说明，两个游标相加的和大于target-第一个数
   * 
   * 所以需要将大的那一个游标向前移动，来减少他们的和，所以尾部游标向前移动一位
   * 
   * 直到两个游标相遇
   */
  class Solution {
    public int threeSumClosest(int[] nums, int target) {
      Arrays.sort(nums);
      int minDiff = Integer.MAX_VALUE;
      int result = 0;

      for (int i = 0; i < nums.length - 2; i++) {
        if (i == 0 || nums[i] != nums[i - 1]) {
          int first = nums[i];
          int sum = target - first;

          int lo = i + 1;
          int hi = nums.length - 1;
          while (lo < hi) {
            int twoSum = nums[lo] + nums[hi];
            int diff = sum - twoSum;
            int absDiff = Math.abs(diff);
            if (absDiff < minDiff) {
              minDiff = absDiff;
              result = first + twoSum;
            }
            if (diff > 0) {
              lo++;
            } else if (diff < 0) {
              hi--;
            } else {
              return target;
            }

          }
        }
      }

      return result;
    }
  }


}
