package practice;

/**
 *
 * <code>LeetCode Problem 53<code>
 * <p>
 * <li>Find the contiguous subarray within an array (containing at least one number) which has the
 * largest sum.
 * <li>For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * <li>the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * <li>click to show more practice.
 * <li>More practice:
 * <li>If you have figured out the O(n) solution, try coding another solution using the divide and
 * conquer approach, which is more subtle.
 * </p>
 * 
 */

public class LeetCode_53 {

  public static void main(String argv[]) {
    int[] tmp = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(new LeetCode_53().new Solution().maxSubArray(tmp));
  }

  /**
   * 这题我的方法真的很难用文字解释。。。
   * 
   * 用DP的方法比较简单也好懂
   * 
   * DP的方法就是用一个数组d[i],存以元素i结尾的最大子串，
   * 
   * 我们知道d[0]=nums[0];
   * 
   * d[i] = nums[i] + (d[i-1] > 0 ? d[i-1] : 0);
   * 
   * 在计算d[i]的过程中，记录一个max，也就是d[0]到d[n-1]中间的最大值，就是我们要求的解
   * 
   * 而我的方法就是
   * 
   * 从头开始遍历数组，用一个累加数记录这一段的元素的累加之和
   * 
   * 一旦这个累加之和小于0了，我就从下一个元素再重新开始累加
   * 
   * 这样在遍历的过程中，最大的累加之和就是我们的解。
   * 
   * 至于为什么呢？
   * 
   * 这是因为累加之和为负的时候，这一段累加的部分就不可能和后面的元素一起组成结果范围了，因为你累加是负值
   */
  class Solution {
    public int maxSubArray(int[] nums) {
      int largest = Integer.MIN_VALUE;
      int cur = 0;
      for (int i = 0; i < nums.length; i++) {
        cur += nums[i];
        if (cur <= 0) {
          cur = 0;
          if (nums[i] > largest)
            largest = nums[i];
        } else {
          if (cur > largest)
            largest = cur;
        }
      }
      return largest;
    }
  }
}
