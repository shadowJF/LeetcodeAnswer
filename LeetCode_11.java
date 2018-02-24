package practice;

/**
 *
 * <code>LeetCode Problem 11<code>
 * <p>
 * <li>Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate
 * (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i,
 * 0). Find two lines, which together with x-axis forms a container, such that the container
 * contains the most water.
 * <li>Note: You may not slant the container and n is at least 2.
 * </p>
 * 
 */

public class LeetCode_11 {

  public static void main(String argv[]) {
    int[] height = {1, 2};
    System.out.println(new LeetCode_11().new Solution().maxArea(height));
  }


  /**
   * 这道题要解出来，主要是要发现其中的规律，这时不妨首先在纸上画一张图
   * 
   * 假设我们已经找到了能容纳最多水的两条线，a和b，a在左边，b在右边，并且a的长度小于等于b的长度
   * 
   * 那么我们可以得到如下规律：
   * 
   * 所有在a左边和在b右边的线条的长度都比a短，因为一旦a左边或者b右边有比a长的线条，那么最大面积就不是a和b组成的了
   * 
   * 那么有了这个规律，我们怎么解题呢
   * 
   * 首先，我们先假设，我们要找的最大面积的较短边在左边(较短边在右边的情况类似)
   * 
   * 我们先设置左右两个游标，左边游标最先指向数组的第一个元素，右边游标指向数组的最后一个元素
   * 
   * 假设，左边游标指向的元素，就是我们要找的最大面积的左边（并且是较短边），那么根据规律，他左边的那些线条都比他短，所以我们在游标移动过程中
   * 
   * 会记录一个左边的最大边，只要左游标当前长度大于左边最大边，那么左游标指向的该元素就满足，这时候我们就可以去移动右游标了，假设不满足，则将左游标向右移动。
   * 
   * 找右游标位置时，让右游标从右往左滑，根据规律，直到找到长度大于等于左游标当前元素为止，那么这时候，左游标和右游标的位置就构成了一个满足规律的沟，计算他的面积
   * 
   * 如果大于最大面积则更新最大面积即可
   * 
   * 然后左游标继续右滑，寻找下一个满足规律的沟，直到左右游标相遇
   * 
   * （需要注意的是，左游标向右移动后，再定位一下个右游标位置时，不需要将右游标归位
   * 
   * 而是直接从上次右游标的位置继续往左找，这是因为根据规律，我们知道上次右游标右边的那些元素都是小于上次左游标的元素值的，而这次左游标元素又大于上次左游标元素
   * 
   * 所以上次右游标右边的那些元素肯定都是小于这次左游标当前元素的，这样也就保证了数组的一次扫描）
   * 
   * 这一趟下来，时间复杂度是O(n)
   * 
   * 接着我们假设找到的沟的较短边在右边，则按照上述方式调个个，重来一遍，即可
   * 
   * 所以整体时间复杂度是O(2n)，也就是O(N)的时间复杂度
   */
  class Solution {

    public int maxArea(int[] height) {
      int max_area = 0;


      // 先假设矮的一边在左边

      int leftMax = 0; // 记录左边最高的点的纵坐标

      int leftindex = 0;
      int rightindex = height.length - 1; // 记录左边边界坐标

      while (leftindex < rightindex) {
        int left_cur = height[leftindex];
        if (left_cur <= leftMax) { // 如果
          leftindex++;
          continue;
        } else {
          while (height[rightindex] < left_cur && rightindex > leftindex) {
            rightindex--;
          }
          if (rightindex <= leftindex)
            break;
          int cur_area = (rightindex - leftindex) * left_cur;
          if (max_area < cur_area) {
            max_area = cur_area;
          }

          leftindex++;
          leftMax = left_cur;

        }
      }

      // 再假设矮的一边在右边

      int rightMax = 0; // 记录右边最高的点的纵坐标

      leftindex = 0;
      rightindex = height.length - 1; // 记录左边边界坐标

      while (leftindex < rightindex) {
        int right_cur = height[rightindex];
        if (right_cur <= rightMax) { // 如果
          rightindex--;
          continue;
        } else {
          while (height[leftindex] < right_cur && rightindex > leftindex) {
            leftindex++;
          }
          if (rightindex <= leftindex)
            break;
          int cur_area = (rightindex - leftindex) * right_cur;
          if (max_area < cur_area) {
            max_area = cur_area;
          }

          rightindex--;
          rightMax = right_cur;

        }
      }

      return max_area;


    }

  }

}
