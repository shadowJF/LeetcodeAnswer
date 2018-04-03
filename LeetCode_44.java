package practice;

/**
 *
 * <code>LeetCode Problem 44<code>
 * <p>
 * <li>Implement wildcard pattern matching with support for '?' and '*'.
 * <li>'?' Matches any single character.
 * <li>'*' Matches any sequence of characters (including the empty sequence).
 * <li>The matching should cover the entire input string (not partial).
 * <li>The function prototype should be:
 * <li>bool isMatch(const char *s, const char *p)
 * <li>Some examples:
 * <li>isMatch("aa","a") → false
 * <li>isMatch("aa","aa") → true
 * <li>isMatch("aaa","aa") → false
 * <li>isMatch("aa", "*") → true
 * <li>isMatch("aa", "a*") → true
 * <li>isMatch("ab", "?*") → true
 * <li>isMatch("aab", "c*a*b") → false
 * </p>
 * 
 */

public class LeetCode_44 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_44().new Solution().isMatch("aaaa", "***a"));
  }

  class Solution {

    /**
     * 这种方法比较巧妙
     * 
     * 他利用了一个规律
     * 
     * 那就是，只要pattern中匹配到了*，那么这之前所有的匹配都是有效的，我们需要考虑的只是从这个*开始之后的匹配
     * 
     * 所以我们需要一个s_index代表输入字符串s的指针，一个p_index作为pattern的指针
     * 
     * 一个last_match代表之前已经完成匹配的s的位置
     * 
     * 一个last_x代表上一个*的位置
     * 
     * 我们对s_index循环，若s_index大于了s的长度，就说明，s所有部分都匹配到了，我们跳出循环后就只需要检查p是否还有*之外的字符
     * 
     * 在循环里，我们首先判断p的当前字符
     * 
     * 如果p不为*，那么p要么是？要么是字符，如果p当前字符和s当前字符匹配，那么就让s和p的指针都往后移一位
     * 
     * 如果p为*，那么我们知道这之前的s和p是已经完全匹配了，所以让last_match为s_index，让last_x为p_index
     * 
     * 然后p后移一位，接着去匹配
     * 
     * 如果p不为*，同时又和s不匹配，那么我们需要看看看这之前有没有*，如果没有，也就是last_x==-1
     * 
     * 那么直接return false，因为没法匹配
     * 
     * 如果之前有*，也就是last_x！=-1
     * 
     * 那么说明，上一个*之后的匹配是有问题的，我们需要让p重回last_x+1
     * 
     * 然后让last_match+1
     * 
     * 就是说让上一个*再多匹配一个s里的字符串，让s_index等于last_match
     * 
     * 这样一直匹配到s_index>=s.length()
     * 
     * 最后时间复杂度为O(m*n)
     */
    public boolean isMatch(String s, String p) {
      int s_index = 0;
      int p_index = 0;
      int last_match = 0;
      int last_x = -1;

      while (s_index < s.length()) {
        if (p_index < p.length() && p.charAt(p_index) != '*'
            && (p.charAt(p_index) == '?' || (p.charAt(p_index) == s.charAt(s_index)))) {
          p_index++;
          s_index++;
        } else if (p_index < p.length() && p.charAt(p_index) == '*') {
          last_match = s_index;
          last_x = p_index;
          p_index++;
        } else if (last_x != -1) {
          last_match++;
          s_index = last_match;
          p_index = last_x + 1;
        } else {
          return false;
        }
      }

      while (p_index < p.length()) {
        if (p.charAt(p_index) != '*')
          return false;
        p_index++;
      }
      return true;
    }

    /**
     * 第二种方法相较第一种方法就好理解多了
     * 
     * 用动态规划的方法
     * 
     * 我们用一个二维数组，match[n][m] 来记录s和p的匹配情况
     * 
     * 其中match[i][j] 代表s的前i个字符是否和p的前j个字符匹配
     * 
     * 那么当j= 0 i=0时，因为都没有字符，所以匹配，所以match[0][0] = true
     * 
     * 当j = 0，而i>0时，因为p没有字符，所以都是无法匹配的，因此match[i][0] =false
     * 
     * 当i= 0，而j>0时，因为s没有字符，因此只有p中j前面都是*才能匹配，只要出现一个不是*的，后面的都不匹配了
     * 
     * 这样初始化后，再用一个二重循环去求解dp问题即可
     */
    public boolean isMatch2(String s, String p) {
      boolean[][] match = new boolean[s.length() + 1][p.length() + 1];

      match[0][0] = true;

      for (int i = 1; i <= s.length(); i++) {
        match[i][0] = false;
      }

      for (int i = 1; i <= p.length(); i++) {
        if (p.charAt(i - 1) == '*')
          match[0][i] = true;
        else
          break;
      }

      for (int i = 1; i <= s.length(); i++)
        for (int j = 1; j <= p.length(); j++) {
          if (p.charAt(j - 1) != '*'
              && (p.charAt(j - 1) == '?' || p.charAt(j - 1) == s.charAt(i - 1))) {
            match[i][j] = match[i - 1][j - 1];
          } else if (p.charAt(j - 1) == '*') {
            match[i][j] = match[i - 1][j] || match[i][j - 1];
          } else {
            match[i][j] = false;
          }
        }

      return match[s.length()][p.length()];

    }
  }
}
