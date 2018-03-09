package practice;

/**
 *
 * <code>LeetCode Problem 27<code>
 * <p>
 * <li>Given an array and a value, remove all instances of that value in-place and return the new
 * length.
 * <li>Do not allocate extra space for another array, you must do this by modifying the input array
 * in-place with O(1) extra memory.
 * <li>The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * <li>Example:
 * <li>Given nums = [3,2,2,3], val = 3,
 * <li>Your function should return length = 2, with the first two elements of nums being 2.
 * </p>
 * 
 */

public class LeetCode_27 {

  public static void main(String argv[]) {

  }

  /**
   * 跟上一道题思路一模一样
   */
  class Solution {
    public int removeElement(int[] nums, int val) {
      int index = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] != val) {
          nums[index] = nums[i];
          index++;
        }
      }
      return index;
    }
  }


}
