package practice;

/**
 *
 * <code>LeetCode Problem 38<code>
 * <p>
 * <li>The count-and-say sequence is the sequence of integers with the first five terms as
 * following:
 * <li>1. 1
 * <li>2. 11
 * <li>3. 21
 * <li>4. 1211
 * <li>5. 111221
 * <li>1 is read off as "one 1" or 11.
 * <li>11 is read off as "two 1s" or 21.
 * <li>21 is read off as "one 2, then one 1" or 1211.
 * <li>Given an integer n, generate the nth term of the count-and-say sequence.
 * <li>Note: Each term of the sequence of integers will be represented as a string.
 * <li>Example 1:
 * <li>Input: 1
 * <li>Output: "1"
 * <li>Example 2:
 * <li>Input: 4
 * <li>Output: "1211"
 * </p>
 * 
 */

public class LeetCode_38 {

  public static void main(String argv[]) {

  }

  /**
   * 这道题的难度应该在于读懂题目吧。。
   * 
   * 这道题的意思就是给出一个count and say的序列
   * 
   * 这个序列里，每一个数都是对前一个数的“描述”
   * 
   * 例如第一个数是“1”，那么第二个数就是 one 1，也就是“11”
   * 
   * 第三个数就是“two 1” 也就是21
   * 
   * 第四个数就是one 2 one 1 也就是1211 以此类推
   * 
   * 所以我们就用一个递归算法就解决了。。
   */
  class Solution {
    public String countAndSay(int n) {
      return recursion(1, "1", n);
    }

    private String recursion(int index, String s, int target) {
      if (target == index)
        return s;
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
        int j = i;
        while (j + 1 < s.length() && s.charAt(j) == s.charAt(j + 1))
          j++;
        sb.append(j - i + 1).append(s.charAt(i));
        i = j;
      }
      return recursion(index + 1, sb.toString(), target);
    }
  }
}
