package practice;

/**
 *
 * <code>LeetCode Problem 31<code>
 * <p>
 * <li>Implement next permutation, which rearranges numbers into the lexicographically next greater
 * <li>permutation of numbers. If such arrangement is not possible, it must rearrange it as the
 * lowest
 * <li>possible order (ie, sorted in ascending order). The replacement must be in-place, do not
 * allocate
 * <li>extra memory. Here are some examples. Inputs are in the left-hand column and its
 * corresponding
 * <li>outputs are in the right-hand column. 1,2,3 → 1,3,2 3,2,1 → 1,2,3 1,1,5 → 1,5,1
 * <li>这道题有可能中国人不太懂。。（例如我） 所以这里解释一下，就是给你一串数字，让你调整数字的顺序，使他组成比原数字大的下一个自然数，如果原数字已经是最大组合，则返回最小的数字组合
 * </p>
 * 
 */

public class LeetCode_31 {

  public static void main(String argv[]) {
    int[] nums = {1, 2, 3};
    new LeetCode_31().new Solution().nextPermutation(nums);

  }

  /**
   * 这道题也是一道需要思考的题目
   * 
   * 首先，我们得找规律
   * 
   * 我们先从数组的最后一个数往前遍历
   * 
   * 如果当前这个数，比该元素之后的所有数都大，那么肯定不是换这个数，就继续往前移动
   * 
   * 如果当前这个数，并不比该元素之后的所有数都大，那么我只要找到他后面比他大的最近的那个数，让他们交换后，再将这个index后面的所有数按照从小到大的顺序排列
   * 
   * 就可以了
   * 
   * 如果游标一直移到最开头，都没有找到这样的元素，那么说明，当前就是最大组合的情况，只要将数字倒过来，就是解
   * 
   * 所以看到这里你也许会以为这个时间复杂度是O(nlogn)，因为需要排序
   * 
   * 但实际上你不需要用快排来排序，因为根据规律，你找到第一个后面有比他大的数时，他后面的这些数肯定都是降序排列好的
   * 
   * 例如，3,5,4,2
   * 
   * 按照规律找到3，他后面有比他大的数，而他之后的那些数，5,4,2肯定是降序排列的，不然在3之前就会找到这样的符合条件的数了
   * 
   * 所以这样的排序实际上是O(n),倒过来就行了
   * 
   * 所以实际时间复杂度是O(n)
   * 
   * 好吧，上面都是discussion里面大家的解法，我的解法就没有利用好这个规律
   * 
   * 每次都将后面的数严格按照升序排列，这样时间复杂度最差情况下为O（n^2）
   */
  class Solution {
    public void nextPermutation(int[] nums) {
      int end = nums.length - 1;
      for (int i = nums.length - 2; i >= 0; i--) {
        if (nums[end] > nums[i]) {
          for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > nums[i]) {
              int tmp = nums[j];
              nums[j] = nums[i];
              nums[i] = tmp;
              return;
            }
          }
        } else {
          for (int j = i + 1; j < nums.length; j++) {
            int tmp = nums[j];
            nums[j] = nums[j - 1];
            nums[j - 1] = tmp;
          }
        }
      }
      return;
    }

  }

}
