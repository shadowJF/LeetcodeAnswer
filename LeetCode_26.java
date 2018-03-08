package practice;

/**
 *
 * <code>LeetCode Problem 26<code>
 * <p>
 * <li>Given a sorted array, remove the duplicates in-place such that each element appear only once
 * and return the new length.
 * <li>Do not allocate extra space for another array, you must do this by modifying the input array
 * in-place with O(1) extra memory.
 * <li>Example:
 * <li>Given nums = [1,1,2],
 * <li>Your function should return length = 2, with the first two elements of nums being 1 and 2
 * respectively.
 * <li>It doesn't matter what you leave beyond the new length.
 * </p>
 * 
 */

public class LeetCode_26 {

  public static void main(String argv[]) {
    int[] nums = {1, 2, 3, 4, 5, 6, 6, 7};
    System.out.println(new LeetCode_26().new Solution().removeDuplicates(nums));
    for (int i = 0; i < nums.length; i++) {
      System.out.println(nums[i]);
    }
  }

  /**
   * 这道题还是很简单的，只是不要被题目误导了
   * 
   * 一开始我以为只要输出不重复的元素个数就行了
   * 
   * 实际上，它是要你将这个数组的元素进行重排，将不重复的这些元素放到数组的前面
   * 
   * 方法很简单，设置一个指针，指向下一个待放入元素的位置，然后将找到的不重复的元素填进去
   */
  class Solution {
    public int removeDuplicates(int[] nums) {
      int index = 0;
      for (int i = 0; i < nums.length; i++) {
        if (i == 0 || nums[i] != nums[i - 1]) {
          nums[index] = nums[i];
          index++;
        }
      }
      return index;
    }
  }


}
