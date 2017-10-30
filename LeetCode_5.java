package practice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <code>LeetCode_5<code>
 * <strong></strong>
 * <p>说明：
 * <li></li>
 * </p>
 * 
 * @since NC6.5
 * @version 2017年10月30日 下午1:42:21
 * @author liangjfc
 */

public class LeetCode_5 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_5().new Solution().longestPalindrome("abababababacbababababa"));
  }

  class Solution {

    public class MyStack {
      private String s;
      private int cursor = 0;
      private boolean active = false;
      private boolean dead = false;

      public MyStack(String s) {
        this.s = s;
        this.cursor = s.length() - 1;
      }

      public char getTopChar() {
        return s.charAt(this.cursor);
      }

      public Character getSecondChar() {
        if (this.cursor - 1 < 0)
          return null;
        else
          return s.charAt(this.cursor - 1);
      }

      public boolean isActivated() {
        return this.active;
      }

      public void addChar(char a) {
        this.s += a;
        this.cursor++;
      }

      public List<MyStack> generateNewStack(char a) {
        List<MyStack> result = new ArrayList<MyStack>();
        MyStack newone = this.copy();
        if (newone.getTopChar() == a) {
          newone.backOne(a);
          result.add(newone);
        }
        Character c = newone.getSecondChar();
        if (c != null && c == a) {
          MyStack newone2 = this.copy();
          newone2.backTwo(a);
          result.add(newone2);
        }
        return result;
      }

      public void handleNewChar(char a) {
        if (this.getTopChar() == a) {
          this.backOne(a);
        } else {
          this.dead = true;
        }
      }

      public boolean isDead() {
        return this.dead;
      }

      public void backOne(char a) {
        this.s += a;
        this.cursor--;
        if (cursor < 0)
          this.dead = true;
        this.active = true;
      }

      public void backTwo(char a) {
        this.s += a;
        this.cursor -= 2;
        if (cursor < 0)
          this.dead = true;
        this.active = true;
      }

      public MyStack copy() {
        MyStack tmp = new MyStack(this.s);
        return tmp;
      }

      public String getPalindrome() {
        return this.s.substring(this.cursor + 1);
      }
    }

    public String longestPalindrome(String s) {
      List<MyStack> activeStackList = new ArrayList<MyStack>();
      String firstChar = s.substring(0, 1);
      MyStack initStack = new MyStack(firstChar);
      String longestString = firstChar;
      for (int i = 1; i < s.length(); i++) {
        char curChar = s.charAt(i);
        List<MyStack> tmpList = new ArrayList<MyStack>();

        List<MyStack> newList = initStack.generateNewStack(curChar);
        for (int k = 0; k < newList.size(); k++) {
          MyStack m = newList.get(k);
          if (m.isDead()) {
            String ps = m.getPalindrome();
            if (ps.length() > longestString.length()) {
              longestString = ps;
            }
          } else {
            tmpList.add(m);
          }
        }

        initStack.addChar(curChar);
        for (int j = 0; j < activeStackList.size(); j++) {
          MyStack aStack = activeStackList.get(j);
          aStack.handleNewChar(curChar);
          if (aStack.isDead()) {
            String ps = aStack.getPalindrome();
            if (ps.length() > longestString.length()) {
              longestString = ps;
            }
          } else {
            tmpList.add(aStack);
          }
        }

        activeStackList = tmpList;
      }

      for (int i = 0; i < activeStackList.size(); i++) {
        String ps = activeStackList.get(i).getPalindrome();
        if (ps.length() > longestString.length()) {
          longestString = ps;
        }
      }

      return longestString;
    }

  }
}
