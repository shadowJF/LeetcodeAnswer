package practice;

/**
 *
 * <code>LeetCode Problem 41<code>
 * <p>
 * <li>Given an unsorted integer array, find the first missing positive integer.
 * <li>For example,
 * <li>Given [1,2,0] return 3,
 * <li>and [3,4,-1,1] return 2.
 * <li>Your algorithm should run in O(n) time and uses constant space.
 * </p>
 * 
 */

public class LeetCode_41 {

  public static void main(String argv[]) {}

  /**
   * 这道题真的很巧妙
   * 
   * 我一开始想的，把数组里的正数映射到一个2^31的维的空间，如果某个正数出现了，那么相应的那一维就是1，相反就是0
   * 
   * 但是这样需要一个2^31的空间，不太现实
   * 
   * 看了讨论中的第一名，发现原来这样就可以
   * 
   * 那就是直接利用原数组来将正数放到正确的位置
   * 
   * 也就是，如果一个正数为a，那就将他放到数组的index为a-1的位置，
   * 
   * 这样，都放好后，我再遍历这个数组，遇到的第一个 index+1 不等于nums[index]的，就是第一个misspositive
   * 
   */
  class Solution {
    public int firstMissingPositive(int[] nums) {

      for (int i = 0; i < nums.length; i++) {
        while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
          swap(nums, i, nums[i] - 1);
        }
      }

      for (int i = 0; i < nums.length; i++) {
        if (i + 1 != nums[i])
          return i + 1;
      }

      return nums.length + 1;


    }

    private void swap(int[] nums, int i, int j) {
      int tmp = nums[i];
      nums[i] = nums[j];
      nums[j] = tmp;
    }
  }
}
