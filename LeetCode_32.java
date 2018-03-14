package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <code>LeetCode Problem 32<code>
 * <p>
 * <li>Given a string containing just the characters '(' and ')', find the length of the longest
 * valid (well-formed) parentheses substring.
 * <li>For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * <li>Another example is ")()())", where the longest valid parentheses substring is "()()", which
 * has length = 4.
 * </p>
 * 
 */

public class LeetCode_32 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_32().new Solution().longestValidParentheses("(())((()(())"));

  }

  /**
   * 这道题，是hard难度，但其实想通了也不难， 我一开始没想通还是走了不少弯路
   * 
   * 首先，我们这里还是用栈的思路去解决（后面看到有用DP的思路的，也很好）
   * 
   * 这里我们自己实现了一个栈，这个栈有一些改造，栈里的元素是一个字符加一个index，代表该字符在字符串中的索引
   * 
   * 这样，我们从头开始遍历字符串s
   * 
   * 如果遇到(,就将(push到栈里
   * 
   * 如果遇到),那么首先判断此时，栈里是否有元素，如果有，说明存在可以和该)匹配的(,那么pop出栈顶的(,以及它的index
   * 
   * 这里我们知道此时)和pop出的(组成的肯定是一个符合规定的括号组合，他的长度为)和(的index之间的差+1
   * 
   * 但是这并不代表以该)结束的最长括号组合的长度就是上面那个值，以为我们还要考虑，和他匹配的(左边是否还有符合要求的括号组合
   * 
   * 如果有，那么需要将他左边的括号组合的长度也加上，才是以该)结束的最长括号组合的长度
   * 
   * 所以我们用一个map存以某个index下的括号结束的最长组合长度，这样，我们获得(的index后，只要去index-1在map中的值
   * 
   * 加上，当前左右括号匹配后的长度，就是以当前)结束的最长括号组合长度了，然后更新到map中，
   * 
   * 同时和max比较更新max值
   * 
   * 第二种方式DP的，就是用一个数组a[i] 去存 以s[i] 结尾的括号组合的长度
   * 
   * 那么如果s[i] = '(' 则a[i] == 0;
   * 
   * 如果s[i] == ')' 则如果s[i-a[i-1]-1] == '(' 则 a[i] = a[i-1] +2 + a[i-a[i-1]-2];
   * 
   * 为什么是上面这个，你自己画个图好好思考，肯定能知道为什么
   */
  class Solution {
    class Stack {
      List<Character> s = new ArrayList<Character>();
      List<Integer> n = new ArrayList<Integer>();

      public void push(Character str, Integer index) {
        s.add(str);
        n.add(index);
      }

      public Character popStr() {
        return s.size() == 0 ? null : s.remove(s.size() - 1);
      }

      public Integer popIndex() {
        return n.size() == 0 ? null : n.remove(n.size() - 1);
      }

      public Character getTopStr() {
        return s.size() == 0 ? null : s.get(s.size() - 1);
      }

      public Integer getTopIndex() {
        return n.size() == 0 ? null : n.get(n.size() - 1);
      }

      public int getSize() {
        return s.size();
      }
    }

    public int longestValidParentheses(String s) {
      if (s == null)
        return 0;

      Stack stack = new Stack();
      Integer max = 0;
      Map<Integer, Integer> map = new HashMap<Integer, Integer>();

      for (int i = 0; i < s.length(); i++) {
        Character c = s.charAt(i);
        if (c == '(') {
          stack.push(c, i);
        } else {
          if (stack.getSize() > 0) {
            stack.popStr();
            Integer pre = stack.popIndex();
            Integer beforeLen = map.getOrDefault(pre - 1, 0);
            Integer curLen = beforeLen + i - pre + 1;
            map.put(i, curLen);
            if (curLen > max) {
              max = curLen;
            }
          }
        }
      }

      return max;

    }

    int longestValidParentheses2(String s) {
      if (s.length() <= 1)
        return 0;
      int curMax = 0;
      int[] longest = new int[s.length()];
      for (int i = 1; i < s.length(); i++) {
        if (s.charAt(i) == ')' && i - longest[i - 1] - 1 >= 0
            && s.charAt(i - longest[i - 1] - 1) == '(') {
          longest[i] = longest[i - 1] + 2
              + ((i - longest[i - 1] - 2 >= 0) ? longest[i - longest[i - 1] - 2] : 0);
          curMax = max(longest[i], curMax);
        }
      }
      return curMax;
    }

    int max(int a, int b) {
      return a > b ? a : b;
    }
  }

}
