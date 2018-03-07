package practice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 22<code>
 * <p>
 * <li>Given n pairs of parentheses, write a function to generate all combinations of well-formed
 * parentheses.
 * <li>For example, given n = 3, a solution set is:
 * <li>[
 * <li>"((()))",
 * <li>"(()())",
 * <li>"(())()",
 * <li>"()(())",
 * <li>"()()()"
 * <li>]
 * </p>
 * 
 */

public class LeetCode_22 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_22().new Solution().generateParenthesis(3));
  }

  class Solution {
    public List<String> generateParenthesis(int n) {
      List<String> result = new ArrayList<String>();
      this.add(result, 0, 0, n, "");
      return result;
    }

    private void add(List<String> result, int left_index, int right_index, int n, String s) {
      if (left_index == n && right_index == n) {
        result.add(s);
        return;
      }

      if (left_index == right_index) {
        s += "(";
        left_index++;
        add(result, left_index, right_index, n, s);
      } else {
        if (left_index == n) {
          s += ")";
          right_index++;
          add(result, left_index, right_index, n, s);
        } else {
          s += "(";
          left_index++;
          add(result, left_index, right_index, n, s);

          left_index--;
          s = s.substring(0, s.length() - 1);

          s += ")";
          right_index++;
          add(result, left_index, right_index, n, s);
        }
      }
    }
  }
}
