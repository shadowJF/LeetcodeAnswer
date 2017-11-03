package practice;

/**
 *
 * <code>LeetCode Problem 8<code>
 * <p>
 * <li>Implement atoi to convert a string to an integer.</li>
 * <li>Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.</li>
 * <li>Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.</li>
 * </p>
 * 
 */

public class LeetCode_8 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_8().new Solution().myAtoi("+-2"));
  }



  /**
   * 
   * 这道题跟第七题一样，也是没啥难度，但是考察你的心思细腻程度的
   *
   * 通常你信心满满地做完后提交，会发现有一堆问题是你没有考虑过的。
   *
   * 譬如，我本以为我考虑的比较全面了，但是还是疏忽了一些情况
   *
   * 我的解题思路很简单，先判断第一个字符是不是符号（但是字符串要先做trim，这也是一个坑）
   *
   * 然后对接下来的字符串，依次取一个字符，判断是不是数字，如果是。则继续处理，如果不是则返回之前得到的结果
   *
   * 如果是字符。则将之前的结果乘以10再加上该字符表示的数字，得到新的结果
   *
   * 但是，在这里需要判断是否会溢出的问题
   *
   * 主要是在乘10之前，要判断乘以10之后是不是会溢出
   *
   * 在乘以10之后，要判断加上当前数字后是否会溢出，这一步还要分正负数字判断，因为正负的数字范围不一样。负数最多到-2147483648，而正数最多只到2147483647
   *
   * 如果溢出，则返回最大正数，或最小负数
   */
  class Solution {
    public int myAtoi(String str) {
      str = str.trim();
      if (str == null || str.length() == 0) {
        return 0;
      }

      int sign = 1;
      int beginIndex = 0;

      char first = str.charAt(0);
      if (first == '-') {
        sign = -1;
        beginIndex = 1;
      } else if (first == '+') {
        sign = 1;
        beginIndex = 1;
      }

      int result = 0;

      while (beginIndex < str.length()) {
        String curS = str.substring(beginIndex, beginIndex + 1);
        if (curS.charAt(0) > '9' || curS.charAt(0) < '0')
          return result * sign;
        if (result > 214748364) {
          if (sign == 1) {
            return Integer.MAX_VALUE;
          } else {
            return Integer.MIN_VALUE;
          }
        }
        result = result * 10;
        Integer curI = Integer.parseInt(curS);
        if (sign == 1) {
          if (Integer.MAX_VALUE - result < curI)
            return Integer.MAX_VALUE;
          else {
            result += curI;
          }
        } else {
          if (Integer.MAX_VALUE - result < curI - 1)
            return Integer.MIN_VALUE;
          else {
            if (Integer.MAX_VALUE - result == curI - 1) {
              if (beginIndex == str.length() - 1) {
                return Integer.MIN_VALUE;
              } else {
                return 0;
              }
            } else
              result += curI;
          }
        }
        beginIndex++;
        if (beginIndex >= str.length())
          break;
      }

      return result * sign;
    }

  }

}
