package practice;

/**
 *
 * <code>LeetCode Problem 9<code>
 * <p>
 * <li>Implement regular expression matching with support for '.' and '*'.</li>
 * <li>'.' Matches any single character.
 * <li>'*' Matches zero or more of the preceding element.
 * <li>The matching should cover the entire input string (not partial).
 * <li>The function prototype should be:
 * <li>bool isMatch(const char *s, const char *p)
 * <li>Some examples:
 * <li>isMatch("aa","a") → false
 * <li>isMatch("aa","aa") → true
 * <li>isMatch("aaa","aa") → false
 * <li>isMatch("aa", "a*") → true
 * <li>isMatch("aa", ".*") → true
 * <li>isMatch("ab", ".*") → true
 * <li>isMatch("aab", "c*a*b") → true
 * </p>
 * 
 */

public class LeetCode_10 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_10().new Solution().isMatch("abbc", "a*b*"));
  }



  /**
   * 
   * 哇，这道题虽然是hard难度，但是我一次提交就过了，开心！
   * 
   * 还是说下这道题的解题思路吧
   * 
   * 一看到这道题，我就隐约觉得，是需要递归求解的，但是具体怎么个递归法，我还不确定，先上手写代码再说
   * 
   * 首先，我们设置两个指针，分别对应输入字符串s和正则串p，这两个指针分别代表s和p当前字符的位置，最初肯定都是0
   * 
   * 那么接下来，就判断指针当前位置的s和p的字符是否匹配即可，如果匹配，则移动指针，继续往下匹配，直到完全匹配上为止
   * 
   * 如果不匹配，则返回
   * 
   * 那么判断s和p的当前字符是否匹配，我们就需要考虑多种情况，这里，我是直接列举了四种情况，来分别讨论的
   * 
   * 情况一：p当前字符是普通字符，且p的下一个字符不是*，这种情况下，首先判断s当前字符是否为空，若为空，则不匹配，直接返回，若不为空，判断当前s和p的字符是否相等，若不相等，则不匹配直接返回，
   * 若相等，则将s和p的指针都向后移动一位
   * 
   * 情况二：p当前字符是普通字符，且p的下一个字符是*，这种情况下，首先判断s当前字符是否为空，若为空，不代表不匹配，可以直接将p指针向后移动两位（因为还有*所以要移动两位），
   * 如果s当前字符不为空 则判断s和p的当前字符是否相等，若相等，则将s指针向后移动一位，或者将p指针向后移动两位都是可以的，若不相等，则只能将p指针向后移动两位
   * 
   * 情况三：p当前字符是‘。’,且p的下一个字符不是*，这种情况下，首先判断，s当前字符是否为空，若为空，代表不匹配，直接返回，若不为空，则将s和p的指针都向后移动一位
   * 
   * 情况四：p当前字符是‘。’，且p的下一个字符是*，这种情况下，首先判断，s当前字符是否为空，若为空，将p指针向后移动两位，若不为空，则可以将s指针向后移动一位，或者将p指针向后移动两位
   * 
   * 最后就是边界情况，当p当前字符和s当前字符都是空的时候，代表完全匹配上了，那么就将全局变量result置为true
   * 
   * 如果p当前字符为空，但是s当前字符不为空，代表无法匹配，那么直接返回
   * 
   * 而至于p当前字符不为空的情况，就需要按上述四种情况来计算了。
   */
  class Solution {
    private boolean result;

    public boolean isMatch(String s, String p) {
      isMatch(s, 0, p, 0);
      return result;
    }

    private void isMatch(String s, int s_index, String p, int p_index) {
      if (result)
        return;

      Character p_cur = p_index < p.length() ? p.charAt(p_index) : null;
      Character s_cur = s_index < s.length() ? s.charAt(s_index) : null;

      if (p_cur == null && s_cur == null) {
        result = true;
        return;
      }

      if (p_cur == null && s_cur != null)
        return;

      Character p_cur_next = p_index >= (p.length() - 1) ? null : p.charAt(p_index + 1);

      // 第一种情况，p为一个字符，且后面没有跟*
      if (!p_cur.equals('.') && (p_cur_next == null || !p_cur_next.equals('*'))) {
        if (s_cur == null)
          return;
        if (!p_cur.equals(s_cur))
          return;
        else {
          isMatch(s, s_index + 1, p, p_index + 1);
        }
      }

      // 第二种情况，p为一个字符，且后面跟着*
      if (!p_cur.equals('.') && (p_cur_next != null && p_cur_next.equals('*'))) {
        if (s_cur == null) {
          isMatch(s, s_index, p, p_index + 2);
        } else if (p_cur.equals(s_cur)) {
          isMatch(s, s_index + 1, p, p_index);
          isMatch(s, s_index, p, p_index + 2);
        } else {
          isMatch(s, s_index, p, p_index + 2);
        }
      }

      // 第三种情况，p为'.',且后面没有跟着*
      if (p_cur.equals('.') && (p_cur_next == null || !p_cur_next.equals('*'))) {
        if (s_cur == null) {
          return;
        } else {
          isMatch(s, s_index + 1, p, p_index + 1);
        }
      }

      // 第四种情况，P为‘.’，且后面跟着*
      if (p_cur.equals('.') && (p_cur_next != null && p_cur_next.equals('*'))) {
        if (s_cur == null)
          isMatch(s, s_index, p, p_index + 2);
        else {
          isMatch(s, s_index + 1, p, p_index);
          isMatch(s, s_index, p, p_index + 2);
        }
      }

    }
  }

}
