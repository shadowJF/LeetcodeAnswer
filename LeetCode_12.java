package practice;

/**
 *
 * <code>LeetCode Problem 12<code>
 * <p>
 * <li>Given an integer, convert it to a roman numeral.
 * <li>Input is guaranteed to be within the range from 1 to 3999.
 * </p>
 * 
 */

public class LeetCode_12 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_12().new Solution().intToRoman(1));
  }


  /**
   * 这道题我一开始是懵逼的，因为我并不知道罗马数字是怎么表示
   * 
   * 所以首先，我们需要学习下罗马数字的表示方式：
   * 
   * https://www.douban.com/note/335254352/
   * 
   * 然后结合下面的代码，你就知道了
   * 
   * 这个代码是discussion里面排第一的，确实好理解
   */
  class Solution {

    public String intToRoman(int num) {
      String M[] = {"", "M", "MM", "MMM"}; // 千位数字
      String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}; // 百位数字表示
      String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}; // 十位数字表示
      String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}; // 个位数字表示
      return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

  }

}
