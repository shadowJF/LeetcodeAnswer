package practice;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <code>LeetCode Problem 12<code>
 * <p>
 * <li>Given a roman numeral, convert it to an integer.
 * <li>Input is guaranteed to be within the range from 1 to 3999.
 * </p>
 * 
 */

public class LeetCode_13 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_13().new Solution().romanToInt("MCD"));
  }



  /**
   * 这道题是上一道题的翻转
   * 
   * 在上一道题我们知道了罗马数字的构成方式
   * 
   * 这一道题，我们需要将罗马数字转化成阿拉伯数字
   * 
   * 一种简单的方式就是，我的第一种方法，从前遍历字符串
   * 
   * 判断当前字符是什么，然后根据当前字符去看接下来的几个字符是什么，从而决定这些字符组成的数字是多少
   * 
   * 然而这样的代码写起来很费劲，也很长，虽然时间复杂度也是O(n)
   * 
   * 那么看下romanToInt2方法
   * 
   * 这就很简洁，思路也很巧妙
   * 
   * 他首先将各个罗马字符对应的数字放到map中
   * 
   * 然后从后往前，遍历字符串
   * 
   * 获取当前字符所对应的数值，然后判断该数值是否小于目前得到的数 以及 是否和前一个字符不同
   * 
   * 如果是，则减去该数值
   * 
   * 如果不是，则加上该数值
   */
  class Solution {
    public int romanToInt(String s) {
      int index = 0;

      int result = 0;

      while (index < s.length()) {

        char c = s.charAt(index);
        Character c1 = (index + 1) < s.length() ? s.charAt(index + 1) : null; // c后面第一个字符
        Character c2 = (index + 2) < s.length() ? s.charAt(index + 2) : null; // c后面第二个字符
        Character c3 = (index + 3) < s.length() ? s.charAt(index + 3) : null; // c后面第三个字符

        if (c == 'M') {
          if (c1 != null && c1 == 'M' && c2 != null && c2 == 'M') {
            result += 3000;
            index += 3;
          } else if (c1 != null && c1 == 'M') {
            result += 2000;
            index += 2;
          } else {
            result += 1000;
            index += 1;
          }
        } else if (c == 'C') {
          if (c1 != null && c1 == 'C' && c2 != null && c2 == 'C') {
            result += 300;
            index += 3;
          } else if (c1 != null && c1 == 'C') {
            result += 200;
            index += 2;
          } else if (c1 != null && c1 == 'D') {
            result += 400;
            index += 2;
          } else if (c1 != null && c1 == 'M') {
            result += 900;
            index += 2;
          } else {
            result += 100;
            index += 1;
          }
        } else if (c == 'X') {
          if (c1 != null && c1 == 'X' && c2 != null && c2 == 'X') {
            result += 30;
            index += 3;
          } else if (c1 != null && c1 == 'X') {
            result += 20;
            index += 2;
          } else if (c1 != null && c1 == 'L') {
            result += 40;
            index += 2;
          } else if (c1 != null && c1 == 'C') {
            result += 90;
            index += 2;
          } else {
            result += 10;
            index += 1;
          }
        } else if (c == 'I') {
          if (c1 != null && c1 == 'I' && c2 != null && c2 == 'I') {
            result += 3;
            index += 3;
          } else if (c1 != null && c1 == 'I') {
            result += 2;
            index += 2;
          } else if (c1 != null && c1 == 'V') {
            result += 4;
            index += 2;
          } else if (c1 != null && c1 == 'X') {
            result += 9;
            index += 2;
          } else {
            result += 1;
            index += 1;
          }
        } else if (c == 'D') {
          if (c1 != null && c1 == 'C' && c2 != null && c2 == 'C' && c3 != null && c3 == 'C') {
            result += 800;
            index += 4;
          } else if (c1 != null && c1 == 'C' && c2 != null && c2 == 'C') {
            result += 700;
            index += 3;
          } else if (c1 != null && c1 == 'C') {
            result += 600;
            index += 2;
          } else {
            result += 500;
            index += 1;
          }
        } else if (c == 'L') {

          if (c1 != null && c1 == 'X' && c2 != null && c2 == 'X' && c3 != null && c3 == 'X') {
            result += 80;
            index += 4;
          } else if (c1 != null && c1 == 'X' && c2 != null && c2 == 'X') {
            result += 70;
            index += 3;
          } else if (c1 != null && c1 == 'X') {
            result += 60;
            index += 2;
          } else {
            result += 50;
            index += 1;
          }

        } else if (c == 'V') {

          if (c1 != null && c1 == 'I' && c2 != null && c2 == 'I' && c3 != null && c3 == 'I') {
            result += 8;
            index += 4;
          } else if (c1 != null && c1 == 'I' && c2 != null && c2 == 'I') {
            result += 7;
            index += 3;
          } else if (c1 != null && c1 == 'I') {
            result += 6;
            index += 2;
          } else {
            result += 5;
            index += 1;
          }
        }
      }

      return result;
    }

    public int romanToInt2(String s) {
      Map<Character, Integer> map = new HashMap<Character, Integer>();
      map.put('I', 1);
      map.put('V', 5);
      map.put('X', 10);
      map.put('L', 50);
      map.put('C', 100);
      map.put('D', 500);
      map.put('M', 1000);

      int number = 0;
      char prev = '#';
      for (int i = s.length() - 1; i >= 0; i--) {
        char ch = s.charAt(i);
        int value = map.get(ch);
        if (value < number && ch != prev) {
          number = number - value;
        } else {
          number = number + value;
        }
        prev = ch;
      }
      return number;

    }

    // class Solution {
    //
    // public String intToRoman(int num) {
    // String M[] = {"", "M", "MM", "MMM"}; // 千位数字
    // String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}; // 百位数字表示
    // String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}; // 十位数字表示
    // String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}; // 个位数字表示
    // return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    // }
    //
    // }
  }

}
