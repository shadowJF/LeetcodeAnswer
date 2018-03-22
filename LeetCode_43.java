package practice;

/**
 *
 * <code>LeetCode Problem 43<code>
 * <p>
 * 
 * </p>
 * 
 */

public class LeetCode_43 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_43().new Solution().multiply("140", "721"));
  }

  /**
   * 这道题我的解法就是按照正常小学学的乘法的方法去解
   * 
   * 首先选择一个较小的字符串b作为乘数，其长度为n
   * 
   * 那么new 一个数组，长度为n，用来存b的每一位数乘以a后得到的字符串
   * 
   * 最后对这n个字符串错位相加，得到最终的答案，记得去掉最前面的0
   * 
   * 然而看了别人的答案，就能知道你和大神之间的差距
   * 
   * https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation
   * 
   * 
   */
  class Solution {
    public String multiply(String num1, String num2) {
      if (num1.length() < num2.length()) {
        String tmp = num1;
        num1 = num2;
        num2 = tmp;
      }

      String[] tmp = new String[num2.length()];

      for (int i = num2.length() - 1; i >= 0; i--) {
        int c = (int) num2.charAt(i) - 48;
        int s = 0;
        StringBuilder sb = new StringBuilder();
        for (int j = num1.length() - 1; j >= 0; j--) {
          int b = (int) num1.charAt(j) - 48;
          int p = c * b + s;
          int g = p % 10;
          s = p / 10;
          sb.append(g);
        }
        if (s != 0)
          sb.append(s);
        tmp[num2.length() - i - 1] = sb.toString();
      }

      StringBuilder sb = new StringBuilder();
      int index = 0;
      int s = 0;
      while (true) {
        if (tmp[num2.length() - 1].length() - 1 < index - num2.length() + 1)
          break;
        for (int i = 0; i < num2.length(); i++) {
          int cindex = index - i;
          if (cindex >= 0 && cindex < tmp[i].length()) {
            s += (int) tmp[i].charAt(cindex) - 48;
          }
        }
        sb.insert(0, s % 10);
        s = s / 10;
        index++;
      }

      sb.insert(0, s);

      int i;
      for (i = 0; i < sb.length() - 1; i++) {
        if (sb.charAt(i) != '0') {
          break;
        }
      }

      sb.delete(0, i);

      return sb.toString();

    }
  }
}
