package practice;

/**
 *
 * <code>LeetCode Problem 45<code>
 * <p>
 * <li>Given an array of non-negative integers, you are initially positioned at the first index of
 * the array.
 * <li>Each element in the array represents your maximum jump length at that position.
 * <li>Your goal is to reach the last index in the minimum number of jumps.
 * <li>For example:
 * <li>Given array A = [2,3,1,1,4]
 * <li>The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1,
 * then 3 steps to the last index.)
 * <li>Note:
 * <li>You can assume that you can always reach the last index.
 * </p>
 * 
 */

public class LeetCode_45 {

  public static void main(String argv[]) {
    int[] tmp = {2, 3, 1, 1, 4};
    System.out.println(new LeetCode_45().new Solution().jump(tmp));
  }

  /**
   * 这道题虽然是hard难度，但是好像我还挺快的想出来了
   * 
   * 首先第一次尝试，我用了DP
   * 
   * 用一个数组minJump[i]来存跳到i位置需要的最少次数
   * 
   * 这样，minJump[0]= 0
   * 
   * 为了计算minJump[i], 我需要遍历从0到i-1的所有数j，获得所有nums[j]>i-j的数，从中找出有最小minJump的数k
   * 
   * 那么minJump[i] = minJump[k]+1
   * 
   * 但是很明显这个方法时间复杂度为O(n^2)
   * 
   * 理所当然的超时了
   * 
   * 第二个方法，时间复杂度为O(n)
   * 
   * 我们想一下，假设第一个元素为4，那么从第二个元素到第五个元素的minJump都是1，
   * 
   * 然后我们计算从第二个元素到第五个元素起跳，最远能跳到哪，最近肯定能跳到第六个元素
   * 
   * 最远能跳到哪，我们只要遍历一遍第二到第五个元素即可
   * 
   * 那么我们就知道第六个元素到最远那个元素(假设为9)，这些位置只需要跳2次就到了
   * 
   * 那么这样我们就知道规律了
   * 
   * 每次用两个坐标，start和end记录下我们已知的最少需要跳几次的元素范围，初始start跟end肯定都等于0
   * 
   * 然后，遍历该范围，获得最远能跳到的位置，那么下一个区间就是上一次的[end+1,最远]
   * 
   * 同时jump次数+1
   * 
   * 直到能跳到的最远距离大于等于最后一个元素了，就可以跳出循环，返回jump次数了
   * 
   * 时间复杂度妥妥的O(n)
   */
  class Solution {
    public int jump(int[] nums) {
      int[] minJump = new int[nums.length];
      minJump[0] = 0;

      for (int i = 1; i < nums.length; i++) {
        int min = nums.length;
        for (int j = 0; j < i; j++) {
          if ((j == 0 || minJump[j] > 0) && i - j <= nums[j] && min > minJump[j]) {
            min = minJump[j] + 1;
          }
        }
        minJump[i] = min;
      }

      return minJump[nums.length - 1];
    }

    public int jump2(int[] nums) {
      if (nums.length == 1)
        return 0;
      int start = 0, end = 0, jump = 0;
      while (true) {
        int max = 0;
        for (int i = start; i <= end; i++)
          if (nums[i] + i > max)
            max = nums[i] + i;
        jump++;
        if (max >= nums.length - 1)
          return jump;
        start = end + 1;
        end = max;
      }
    }
  }
}
