package practice;

/**
 *
 * <code>LeetCode Problem 33<code>
 * <p>
 * <li>Suppose an array sorted in ascending order is rotated at some pivot unknown to you
 * beforehand.
 * <li>(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <li>You are given a target value to search. If found in the array return its index, otherwise
 * return -1.
 * <li>You may assume no duplicate exists in the array.
 * </p>
 * 
 */

public class LeetCode_33 {

  public static void main(String argv[]) {
    int[] a = {1, 2, 5, -1, 0};
    System.out.println(new LeetCode_33().new Solution().search(a, 5));

  }

  /**
   * 这道题就是用二分法解决
   * 
   * 首先用二分法获得最小数的index
   * 
   * 然后根据target的大小，决定是在前面还是在后面进行二分查找
   */
  class Solution {
    public int search(int[] nums, int target) {
      if (nums == null || nums.length == 0)
        return -1;

      int middleIndex = searchMiddle(nums, 0, nums.length - 1);

      if (middleIndex == 0) {
        return binarySearch(nums, 0, nums.length - 1, target);
      }

      if (target >= nums[0] && target <= nums[middleIndex - 1]) {
        return binarySearch(nums, 0, middleIndex - 1, target);
      }

      if (target >= nums[middleIndex] && target <= nums[nums.length - 1])
        return binarySearch(nums, middleIndex, nums.length - 1, target);

      return -1;

    }

    private int binarySearch(int[] nums, int lo, int hi, int target) {
      if (lo > hi)
        return -1;
      int middle = (lo + hi) / 2;

      if (nums[middle] == target) {
        return middle;
      }

      if (nums[middle] > target) {
        return binarySearch(nums, lo, middle - 1, target);
      }

      if (nums[middle] < target) {
        return binarySearch(nums, middle + 1, hi, target);
      }

      return -1;
    }

    private int searchMiddle(int[] nums, int lo, int hi) {
      int middle = (hi + lo) / 2;
      if (nums[middle] <= nums[hi] && nums[middle] <= nums[lo]) {
        if (middle - 1 >= lo && nums[middle - 1] > nums[middle]) {
          return middle;
        }
        if (middle - 1 < lo) {
          return middle;
        }
        return searchMiddle(nums, lo, middle - 1);
      }

      if (nums[middle] >= nums[lo] && nums[middle] >= nums[hi]) {
        if (middle + 1 <= hi && nums[middle + 1] < nums[middle])
          return middle + 1;

        return searchMiddle(nums, middle + 1, hi);
      }

      return 0;

    }
  }

}
