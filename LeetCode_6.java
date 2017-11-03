package practice;

/**
 *
 * <code>LeetCode Problem 6<code>
 * <p>
 * <li>The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)</li>
 * <li>P   A   H   N</li>
 * <li>A P L S I I G</li>
 * <li>Y   I   R</li>
 * <li>And then read line by line: "PAHNAPLSIIGYIR"</li>
 * <li>Write the code that will take a string and make this conversion given a number of rows:</li>
 * <li>string convert(string text, int nRows);</li>
 * <li>convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".</li>
 * </p>
 * 
 */

public class LeetCode_6 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_6().new Solution().convert("PAYPALISHIRING", 4));
  }

  /**
   * 这道题还是比较简单的，我最先想到的就是O(N)复杂度的解法，而且空间也是O(N)
   * 
   * 其实只要找到规律，把一个个字符找出来即可，也就是摆成之字形之后，从第一行开始，一行一行地把字符接起来就行了，而每一行，字符之间的相隔数是有规律的
   * 
   * 首先，第一行和最后一行要特殊对待，因为他们都是间隔相同个数的字符后找到下一个字符，具体的规律自己在纸上画一下就知道了
   * 
   * 然后中间的行，需要特殊对待，因为他们第一个字符与第二个字符之间相隔的字符数同第二个字符与第三个字符之间相隔的字符数不一样
   */
  class Solution {
    public String convert(String s, int numRows) {
      StringBuilder sb = new StringBuilder();
      int len = s.length();
      // 先处理第一行
      int index = 0;
      while (index < len) {
        sb.append(s.charAt(index));
        int inc = numRows - 1 + (numRows - 2 > 0 ? numRows - 2 : 0) + 1;
        index += inc;
      }

      // 处理中间的行

      for (int i = 1; i <= numRows - 2; i++) {
        index = i;
        boolean even = false;
        while (index < len) {
          sb.append(s.charAt(index));
          if (!even) {
            int inc = (numRows - i - 1) * 2;
            index += inc;
          } else {
            int inc = i * 2;
            index += inc;
          }
          even = !even;
        }
      }


      // 处理最后一行
      index = numRows - 1;
      while (index < len && index > 0) {
        sb.append(s.charAt(index));
        int inc = numRows - 1 + (numRows - 2 > 0 ? numRows - 2 : 0) + 1;
        index += inc;
      }

      return sb.toString();

    }
  }
}
