package practice;

/**
 *
 * <code>LeetCode Problem 42<code>
 * <p>
 * <li>Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * <li>For example,
 * <li>Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * <li>图片实例看leetcode网站
 * </p>
 * 
 */

public class LeetCode_42 {

  public static void main(String argv[]) {
    int[] tmp = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    System.out.println(new LeetCode_42().new Solution().trap(tmp));
  }

  /**
   * 这道题还是要找规律，不然很难 = =
   * 
   * 我这里给出了三种方法
   * 
   * 第一种自然是我最开始想到的，必然会超时的蠢方法
   * 
   * 后两种都是借鉴的别人的思路，其实我已经快想到了，但就是没想到！！
   * 
   * 而且突然想起来这tm不就是我之前google面试时有个人给我提问的变种吗= =
   * 
   * 他问的是一个3D的情况，那么对于每一格能容纳的水量，就要计算他四周的最长高度abcd，然后取最小的减去该格的高度就是这一格能容纳的水量
   */
  class Solution {

    /**
     * 第一种蠢方法，先计算出数组中的最大值
     * 
     * 然后从最大值到0开始遍历，每一遍都计算这一行能容纳多少水
     * 
     * 也就是算长度到达这个值的两个元素之间的差，累加起来
     * 
     * 但是这样的话 时间复杂度是O(kn)，k是最大值，n是数组长度
     * 
     * 超时！
     */
    public int trap(int[] height) {
      int max = 0;
      for (int i = 0; i < height.length; i++) {
        if (height[i] > max) {
          max = height[i];
        }
      }

      int result = 0;

      for (int i = max; i >= 1; i--) {
        int index = -1;
        for (int j = 0; j < height.length; j++) {
          if (height[j] >= i) {
            if (index >= 0)
              result += j - index - 1;
            index = j;
          }
        }
      }
      return result;
    }

    /**
     * 第二种方法就是利用了规律，啥规律
     * 
     * 就是每一列，能容纳的水量，其实跟他左右两边的最高列 以及自身的高度是密切相关的
     * 
     * 假设第i列，i到n之间最高列是j，0到i之间最高列是k，那么j和k之间较小的那一个减去i的高度，就是i能容纳的水量
     * 
     * 这样的话，我先找到最高的那一列
     * 
     * 然后分左右两边处理
     * 
     * 从左遍历到最高列，记录下左边最高的列的位置，（但这个最高列肯定是小于等于全部最高列的）
     * 
     * 然后每一列能容纳的水量就是左边最高列减去当前列的高度
     * 
     * 同理，从右遍历到最高列，记录下右边最高列的位置，
     * 
     * 然后每一列能容纳的水量也是右边最高列的长度减去当前列的长度
     * 
     * 整个时间复杂度就是O(n)
     */
    public int trap2(int[] height) {
      int max = 0;
      int maxIndex = 0;
      for (int i = 0; i < height.length; i++) {
        if (height[i] > max) {
          maxIndex = i;
          max = height[i];
        }
      }

      int result = 0;

      for (int i = 0, tallest = 0; i < maxIndex; i++) {
        if (tallest > height[i])
          result += tallest - height[i];
        tallest = Math.max(tallest, height[i]);
      }

      for (int i = height.length - 1, tallest = 0; i > maxIndex; i--) {
        if (tallest > height[i])
          result += tallest - height[i];
        tallest = Math.max(tallest, height[i]);
      }

      return result;

    }

    /**
     * 有了第二种方法的思路
     * 
     * 第三种方法就更巧妙了
     * 
     * 因为我们知道，只要知道左右两边的最高列的高度（其实只要自己高就行）
     * 
     * 那我们也不需要去知道最高的那一列是哪个
     * 
     * 我们只要用两个指针，一个指向左边，一个指向右边，先判断左右两边哪个高，如果右边高，那么，就算左边的能容纳的水量
     * 
     * 因为右边高，所以右边能作为边界，那我只要维护一个左边的最高列，就能知道当前左指针列能容纳的水量的了
     * 
     * 同理，当左边高时，就能计算右边列能容纳的水量
     */
    public int trap3(int[] height) {
      int left = 0;
      int right = height.length - 1;

      int result = 0;

      int leftMax = 0;
      int rightMax = 0;

      while (left < right) {
        if (height[left] < height[right]) {
          if (leftMax > height[left])
            result += leftMax - height[left];
          leftMax = Math.max(leftMax, height[left]);
        } else {
          if (rightMax > height[right])
            result += rightMax - height[right];
          rightMax = Math.max(rightMax, height[right]);
        }
      }
      return result;
    }
  }
}
