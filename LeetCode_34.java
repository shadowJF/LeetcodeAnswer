package practice;

/**
 *
 * <code>LeetCode Problem 33<code>
 * <p>
 * <li>Given an array of integers sorted in ascending order, find the starting and ending position
 * of a given target value.
 * <li>Your algorithm's runtime complexity must be in the order of O(log n).
 * <li>If the target is not found in the array, return [-1, -1].
 * <li>For example,
 * <li>Given [5, 7, 7, 8, 8, 10] and target value 8,
 * <li>return [3, 4].
 * </p>
 * 
 */

public class LeetCode_34 {

  public static void main(String argv[]) {
    int[] a = {0, 0, 1, 2, 2};
    System.out.println(new LeetCode_34().new Solution().searchRange(a, 0)[0]);
    System.out.println(new LeetCode_34().new Solution().searchRange(a, 0)[1]);
  }

  /**
   * 就是一个二分查找问题
   * 
   * 只是和普通的二分查找有一点不同
   * 
   * 在找到目标值后，得判断是不是他的前一个数 或者 后一个数 是不同的数，如果是，代表真的找到了
   * 
   * 如果不是，则还需要二分，直到找到边上的那个数
   */
  class Solution {
    public int[] searchRange(int[] nums, int target) {
      int[] result = {-1, -1};
      if (nums == null || nums.length == 0)
        return result;

      int first = this.binarySearch1(nums, 0, nums.length - 1, target);
      if (first == -1) {
        return result;
      } else {
        int second = this.binarySearch2(nums, first, nums.length - 1, target);
        result[0] = first;
        result[1] = second;
        return result;
      }
    }

    private int binarySearch1(int[] nums, int lo, int hi, int target) {
      if (lo > hi)
        return -1;

      int middle = (lo + hi) / 2;

      if (nums[middle] == target && (middle == lo || nums[middle - 1] != target))
        return middle;
      else if (nums[middle] == target) {
        return binarySearch1(nums, lo, middle - 1, target);
      } else if (nums[middle] > target) {
        return binarySearch1(nums, lo, middle - 1, target);
      } else {
        return binarySearch1(nums, middle + 1, hi, target);
      }
    }

    private int binarySearch2(int[] nums, int lo, int hi, int target) {
      if (lo > hi)
        return -1;

      int middle = (lo + hi) / 2;

      if (nums[middle] == target && (middle == hi || nums[middle + 1] != target))
        return middle;
      else if (nums[middle] == target) {
        return binarySearch2(nums, middle + 1, hi, target);
      } else if (nums[middle] > target) {
        return binarySearch2(nums, lo, middle - 1, target);
      } else {
        return binarySearch2(nums, middle + 1, hi, target);
      }
    }
  }

}
