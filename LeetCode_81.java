package practice;

/**
 *
 * <code>LeetCode Problem 81<code>
 * <p>
 * <li>Suppose an array sorted in ascending order is rotated at some pivot unknown to you
 * beforehand.
 * <li>(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 * <li>You are given a target value to search. If found in the array return true, otherwise return
 * false.
 * <li>Example 1:
 * <li>Input: nums = [2,5,6,0,0,1,2], target = 0
 * <li>Output: true
 * <li>Example 2:
 * <li>Input: nums = [2,5,6,0,0,1,2], target = 3
 * <li>Output: false
 * <li>Follow up:
 * <li>This is a follow up problem to Search in Rotated Sorted Array, where nums may contain
 * duplicates.
 * <li>Would this affect the run-time complexity? How and why?
 * </p>
 * 
 */

public class LeetCode_81 {

  public static void main(String argv[]) {
    int[] tmp = {1, 1, 3, 1};
    System.out.println(new LeetCode_81().new Solution().search(tmp, 3));
  }

  /**
   * 这道题，之前第一代，我的解法是先算出中间边界，然后分情况进行二分查找
   * 
   * 而在这里，我是不找中间边界，直接查找
   * 
   * 首先获得middle元素
   * 
   * 然后分三种情况，如果middle=nums[0] 并且middle = nums[nums.length-1];
   * 
   * 那么这种情况下我们是不知道middle是处于前半段还是后半段的，因为元素是可以重复的
   * 
   * 那么我就先当着是前半段，进行二分查找，如果返回false，再进行后半段二分查找，这时的复杂度就不是logN了而是O(n)
   * 
   * 第二种情况,middle<=nums[nums.length-1] 并且middle<=nums[0]，那么middle是处于后半段，进行后半段二分查找，当然这里面也得分情况讨论
   * 
   * 分target>middle和target<middle等情况
   * 
   * 第三种情况，就是middle处于前半段了，这时候进行前半段二分查找，也得taget>middle和target<middle的情况讨论
   */
  class Solution {
    public boolean search(int[] nums, int target) {
      return binarySearch(nums, 0, nums.length - 1, target);
    }

    private boolean binarySearch(int[] nums, int lo, int hi, int target) {
      if (lo > hi)
        return false;
      int middle = (lo + hi) / 2;
      if (nums[middle] == target)
        return true;
      if (nums[middle] == nums[0] && nums[middle] == nums[nums.length - 1]) {
        if (binarySearch(nums, middle + 1, hi, target))
          return true;
        else
          return binarySearch(nums, lo, middle - 1, target);
      } else if (nums[middle] <= nums[nums.length - 1] && nums[middle] <= nums[0]) {
        if (nums[middle] < target && target <= nums[hi])
          return binarySearch(nums, middle + 1, hi, target);
        if (nums[middle] < target && target > nums[hi])
          return binarySearch(nums, lo, middle - 1, target);
        if (nums[middle] > target)
          return binarySearch(nums, lo, middle - 1, target);
      } else {
        if (nums[middle] < target)
          return binarySearch(nums, middle + 1, hi, target);
        if (nums[middle] > target && target >= nums[lo])
          return binarySearch(nums, lo, middle - 1, target);
        if (nums[middle] > target && target < nums[lo])
          return binarySearch(nums, middle + 1, hi, target);
      }
      return false;
    }
  }

}
