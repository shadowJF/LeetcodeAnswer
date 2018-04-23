package practice;

/**
 *
 * <code>LeetCode Problem 80<code>
 * <p>
 * <li>Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at
 * most twice and return the new length.
 * <li>Do not allocate extra space for another array, you must do this by modifying the input array
 * in-place with O(1) extra memory.
 * <li>Example 1:
 * <li>Given nums = [1,1,1,2,2,3],
 * <li>Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2
 * and 3 respectively.
 * <li>It doesn't matter what you leave beyond the returned length.
 * <li>Example 2:
 * <li>Given nums = [0,0,1,1,1,1,2,3,3],
 * <li>Your function should return length = 7, with the first seven elements of nums being modified
 * to 0, 0, 1, 1, 2, 3 and 3 respectively.
 * <li>It doesn't matter what values are set beyond the returned length.
 * <li>Clarification:
 * <li>Confused why the returned value is an integer but your answer is an array?
 * <li>Note that the input array is passed in by reference, which means modification to the input
 * array will be known to the caller as well.
 * <li>Internally you can think of this:
 * <li>// nums is passed in by reference. (i.e., without making a copy)
 * <li>int len = removeDuplicates(nums);
 * <li>// any modification to nums in your function would be known by the caller.
 * <li>// using the length returned by your function, it prints the first len elements.
 * <li>for (int i = 0; i < len; i++) {
 * <li>print(nums[i]);
 * <li>}
 * </p>
 * 
 */

public class LeetCode_80 {

  public static void main(String argv[]) {
    // int[] tmp = {1, 2, 3};
    // System.out.println(new LeetCode_79().new Solution().exist(tmp));
  }

  /**
   * 用一个指针placeIndex指向当前应该放数字的位置
   * 
   * 统计某一个数字出现了多少次了，如果出现了2次及以下，则将该数字放到placeIndex的位置
   * 
   * 同时placeIndex++
   * 
   */
  class Solution {
    public int removeDuplicates(int[] nums) {
      int placeIndex = 0;
      int count = 0;
      for (int i = 0; i < nums.length; i++) {
        if (i > 0 && nums[i] == nums[i - 1])
          count++;
        else
          count = 1;
        if (count <= 2) {
          nums[placeIndex] = nums[i];
          placeIndex++;
        }
      }
      return placeIndex;
    }
  }

}
