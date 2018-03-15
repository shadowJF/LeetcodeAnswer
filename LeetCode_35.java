package practice;

/**
 *
 * <code>LeetCode Problem 35<code>
 * <p>
 * <li>Given a sorted array and a target value, return the index if the target is found. If not,
 * return the index where it would be if it were inserted in order.
 * <li>You may assume no duplicates in the array.
 * <li>Example 1:
 * <li>Input: [1,3,5,6], 5
 * <li>Output: 2
 * <li>Example 2:
 * <li>Input: [1,3,5,6], 2
 * <li>Output: 1
 * <li>Example 3:
 * <li>Input: [1,3,5,6], 7
 * <li>Output: 4
 * <li>Example 1:
 * <li>Input: [1,3,5,6], 0
 * <li>Output: 0
 * </p>
 * 
 */

public class LeetCode_35 {

  public static void main(String argv[]) {
    int[] a = {1, 3, 5, 6};
    System.out.println(new LeetCode_35().new Solution().searchInsert(a, 2));
  }

  /**
   * 这道题也是用二分法查找，但是边界需要考虑清楚
   * 
   * 在我这里，我最终只可能让lo == hi，而不会让他出现 lo > hi的情况
   * 
   * 这样，当lo = hi时，我判断当前lo的值和target的值得大小比较，就能知道target的index了
   * 
   * 那么怎么保证lo最终是等于hi，而不会出现lo> hi的情况呢
   * 
   * 我们知道相邻两个数，取middle时，肯定是取的第一个数
   * 
   * 那么，我们在二分时，前半部分的上界还是lo，但是下届不能是middle-1，而只能是middle，这样就不会使得lo > hi了
   * 
   * 而后半部分的上界则可以是middle+1,下界是hi，这样lo也不会大于hi的
   */
  class Solution {
    public int searchInsert(int[] nums, int target) {
      if (nums.length == 0)
        return 0;
      return search(nums, 0, nums.length - 1, target);
    }

    private int search(int[] nums, int lo, int hi, int target) {
      if (lo == hi) {
        if (target == nums[lo])
          return lo;
        else if (target > nums[lo])
          return lo + 1;
        else
          return lo;
      } else {
        int middle = (lo + hi) / 2;
        if (nums[middle] == target)
          return middle;
        else if (nums[middle] > target) {
          // 当middle大于target时，需要设置上限为middle，这样最终才不会导致hi < lo，最多是hi == lo
          return search(nums, lo, middle, target);
        } else {
          // 当middle小于target时，需要设置下限为middle+1，因为两数取中时，肯定是会取到第一个数
          return search(nums, middle + 1, hi, target);

        }
      }
    }
  }

}
