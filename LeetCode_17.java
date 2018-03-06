package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <code>LeetCode Problem 17<code>
 * <p>
 * <li>Given a digit string, return all possible letter combinations that the number could
 * represent.
 * <li>A mapping of digit to letters (just like on the telephone buttons) is given below.
 * <li>参考电话上的按键
 * <li>Input:Digit string "23"
 * <li>Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * <li>Note:
 * <li>Although the above answer is in lexicographical order, your answer could be in any order you
 * want.
 * </p>
 * 
 */

public class LeetCode_17 {

  public static void main(String argv[]) {
    String s = "23";
    System.out.println(new LeetCode_17().new Solution().letterCombinations(s));
  }

  /**
   * 这道题不难，就是一个递归算法
   * 
   * 递归的每一层，循环遍历，可能的字母，然后进入到下一层
   * 
   */
  class Solution {
    Map<Character, String> map = new HashMap<Character, String>() {
      private static final long serialVersionUID = 1L;

      {
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
      }
    };

    public List<String> letterCombinations(String digits) {
      List<String> result = new ArrayList<String>();
      this.add(result, digits, 0, "");
      return result;
    }

    private void add(List<String> result, String digits, int index1, String letter) {
      if (index1 >= digits.length()) {
        if (letter.length() > 0)
          result.add(letter);
      } else {

        Character c = digits.charAt(index1);
        String s = map.get(c);

        for (int i = 0; i < s.length(); i++) {
          String l = letter;
          Character c2 = s.charAt(i);
          l += c2;
          add(result, digits, index1 + 1, l);
        }
      }
    }


  }
}
