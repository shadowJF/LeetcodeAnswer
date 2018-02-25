package practice;

/**
 *
 * <code>LeetCode Problem 14<code>
 * <p>
 * <li>Write a function to find the longest common prefix string amongst an array of strings.
 * </p>
 * 
 */

public class LeetCode_14 {

  public static void main(String argv[]) {
    String[] tmp = {"a", "aa"};
    System.out.println(new LeetCode_14().new Solution().longestCommonPrefix(tmp));
  }

  /**
   * 这题也是很简单的一题，我的解法相信也是大多数人的解法
   * 
   * 就是取第一个字符串，然后一个个拿该字符串的每个字符，去和数组中剩下的字符去比较，如果都相同，则继续比较下一个字符，如果不相同则之前的是最长公共前缀
   * 
   * 然而这种写法很复杂，如果要追求最少代码数实现，可以参考第二个方法
   */
  class Solution {
    public String longestCommonPrefix(String[] strs) {
      if (strs.length == 0)
        return "";

      String first = strs[0];

      for (int i = 0; i < first.length(); i++) {
        char c = first.charAt(i);

        boolean flag = true;

        for (int j = 1; j < strs.length; j++) {
          String s = strs[j];
          if (i >= s.length() || s.charAt(i) != c) {
            flag = false;
            break;
          }
        }

        if (!flag) {
          return first.substring(0, i);
        }

      }

      return first;
    }


    public String longestCommonPrefix2(String[] strs) {
      if (strs.length == 0)
        return "";
      String pre = strs[0];
      for (int i = 1; i < strs.length; i++)
        while (strs[i].indexOf(pre) != 0)
          pre = pre.substring(0, pre.length() - 1);
      return pre;
    }
  }



}
