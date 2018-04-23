package practice;

/**
 *
 * <code>LeetCode Problem 75<code>
 * <p>
 * <li>Given an array with n objects colored red, white or blue, sort them in-place so that objects
 * of the same color are adjacent, with the colors in the order red, white and blue.
 * <li>Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue
 * respectively.
 * <li>Note: You are not suppose to use the library's sort function for this problem.
 * <li>Example:
 * <li>Input: [2,0,2,1,1,0]
 * <li>Output: [0,0,1,1,2,2]
 * <li>Follow up:
 * <li>A rather straight forward solution is a two-pass algorithm using counting sort.
 * <li>First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with
 * total number of 0's, then 1's and followed by 2's.
 * <li>Could you come up with a one-pass algorithm using only constant space?
 * </p>
 * 
 */

public class LeetCode_75 {

  public static void main(String argv[]) {
    int[] tmp = {1, 2, 0, 1, 2, 0};
    new LeetCode_75().new Solution().sortColors(tmp);
    System.out.println(tmp);
  }

  /**
   * 这道题虽然是中等难度，但我还是想破了脑袋才想出来解法 = =
   * 
   * 首先，题目说了如果用计数排序，两遍遍历就解了
   * 
   * 所以要求用one pass，也就是最多遍历一次来解决
   * 
   * 那么我们就要想一想，为什么会有one pass的解法呢，排序最快的算法也就是计数排序了，也要两遍，那么一遍的解法
   * 
   * 肯定是跟这种独特的数据有关
   * 
   * 我们知道这里面只有三个元素，0,1,2
   * 
   * 那么我们是不是只要遍历这个数组，遇到0，就让它排到数组头，遇到2就排到数组尾，其他的都置为1，不就行了吗
   * 
   * 所以，我们需要两个指针，一个指向下一个0需要占用的位置p1，一个指向下一个2需要占用的位置p2，那么我们从头开始遍历数组
   * 
   * 如果这个数等于0，那么就让p1为0，同时p1++
   * 
   * 如果这个数等于2，那么就让p2为2，同时p2--，并且让p2原来的元素换到当前位置上，因为这个元素还没有被处理，然后当前元素再处理一遍
   * 
   * 当然我下面代码里的处理跟我上面说的还有点不一样，我在遇到2的时候，是从p2开始向前找到第一个不为2的元素（因为如果是2就不用替换），再处理
   */
  class Solution {
    public void sortColors(int[] nums) {
      int zeroIndex = 0;
      int twoIndex = nums.length - 1;
      int index = 0;
      while (index <= twoIndex) {
        int tmp = nums[index];
        nums[index] = 1;
        if (tmp == 0)
          nums[zeroIndex++] = 0;
        if (tmp == 2) {
          while (twoIndex > index && nums[twoIndex] == 2)
            twoIndex--;
          if (nums[twoIndex] == 0)
            nums[zeroIndex++] = 0;
          nums[twoIndex] = 2;
          twoIndex--;
        }
        index++;
      }
    }
  }

}
