package practice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 20<code>
 * <p>
 * <li>Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if
 * the input string is valid.
 * <li>The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and
 * "([)]" are not.
 * </p>
 * 
 */

public class LeetCode_20 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_20().new Solution().isValid("[](){}"));
  }


  /**
   * 这题就是用栈的思想，左括号入栈，右括号的话，则取栈顶元素与之比较，如果是一对括号，则匹配，pop掉栈顶元素
   * 
   * 然后继续向后遍历字符串，直到字符串的所有字符处理完毕
   * 
   * 最后看栈是否为空，如果为空则代表是符合的字符串，如果不为空，则代表该字符串不符合规定
   */
  class Solution {
    class Stack {
      List<Character> s = new ArrayList<Character>();

      public void push(Character str) {
        s.add(str);
      }

      public Character pop() {
        return s.size() == 0 ? null : s.remove(s.size() - 1);
      }

      public Character getTop() {
        return s.size() == 0 ? null : s.get(s.size() - 1);
      }

      public int getSize() {
        return s.size();
      }
    }

    public boolean isValid(String s) {
      if (s == null || s.equals(""))
        return true;
      Stack stack = new Stack();
      for (int i = 0; i < s.length(); i++) {
        Character c = s.charAt(i);
        if (c == '(' || c == '{' || c == '[') {
          stack.push(c);
        } else {
          Character top = stack.getTop();
          if (match(top, c)) {
            stack.pop();
          } else {
            return false;
          }
        }
      }
      if (stack.getSize() == 0) {
        return true;
      } else
        return false;
    }

    private boolean match(Character pr, Character po) {
      if (pr == null || po == null)
        return false;
      if (pr.equals('(') && po.equals(')'))
        return true;
      if (pr.equals('[') && po.equals(']'))
        return true;
      if (pr.equals('{') && po.equals('}'))
        return true;
      return false;
    }

  }
}
