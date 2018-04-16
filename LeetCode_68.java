package practice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 68<code>
 * <p>
 * <li>Given an array of words and a length L, format the text such that each line has exactly L
 * characters and is fully (left and right) justified.
 * <li>You should pack your words in a greedy approach; that is, pack as many words as you can in
 * each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 * <li>Extra spaces between words should be distributed as evenly as possible. If the number of
 * spaces on a line do not divide evenly between words, the empty slots on the left will be assigned
 * more spaces than the slots on the right.
 * <li>For the last line of text, it should be left justified and no extra space is inserted between
 * words.
 * <li>For example,
 * <li>words: ["This", "is", "an", "example", "of", "text", "justification."]
 * <li>L: 16.
 * <li>Return the formatted lines as:
 * <li>[
 * <li>"This is an",
 * <li>"example of text",
 * <li>"justification. "
 * <li>]
 * <li>Note: Each word is guaranteed not to exceed L in length.
 * <li>click to show corner cases.
 * <li>Corner Cases:
 * <li>A line other than the last line might contain only one word. What should you do in this case?
 * <li>In this case, that line should be left-justified.
 * </p>
 * 
 */

public class LeetCode_68 {

  public static void main(String argv[]) {
    String[] tmp = {"Listen", "to", "many,", "speak", "to", "a", "few."};
    System.out.println(new LeetCode_68().new Solution().fullJustify(tmp, 6));
  }

  /**
   * 这道题首先就是要看懂题目
   * 
   * 就是给定一个单词数组和一个长度L
   * 
   * 让你把这些单词拼成一行行的字符串
   * 
   * 每一行的长度必须为L
   * 
   * 每两个单词间至少有一个空格
   * 
   * 且每一行的头和尾都不能有空格（如果能容纳两个以上单词的话，如果只能容纳一个单词，那么左边不能有空格，右边可以有）
   * 
   * 而且如果单词间需要填充多个空格的话，尽可能让每两个单词之间的空格数平均，如果无法平均，多出来的空格往左边填充
   * 
   * 这样的话，我们其实只需要一个List，用来存每一行的字符串
   * 
   * 一个allStrLen来存当前行的字符串长度
   * 
   * 我们从数组头开始遍历，如果allStrLen + 当前字符串的长度+1（空格） 小于等于maxWidth,那么就将当前单词添加到list中 ，并且让allStrLen增加
   * 
   * 如果allStrLen + 当前字符串的长度+1（空格） 大于 maxWidth，那么就取当前list中的字符串来组成一行
   * 
   * 那么我们需要计算每两个单词之间的空格数，这个我们只要知道单词总长度，单词个数，就可以计算得到。
   */
  class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
      List<String> result = new ArrayList<String>();
      List<String> oneLine = new ArrayList<String>();
      int allStrLen = 0;
      int index = 0;
      while (index < words.length) {
        if (index == 0 || allStrLen + 1 + words[index].length() <= maxWidth) {
          allStrLen += index == 0 ? words[index].length() : 1 + words[index].length();
          oneLine.add(words[index]);
        } else {
          int space = oneLine.size() > 1
              ? (maxWidth - (allStrLen - oneLine.size() + 1)) / (oneLine.size() - 1)
              : 0;
          int extraSpace = oneLine.size() > 1
              ? (maxWidth - (allStrLen - oneLine.size() + 1)) % (oneLine.size() - 1)
              : 0;
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < oneLine.size(); i++) {
            sb.append(oneLine.get(i));
            if (i != oneLine.size() - 1) {
              for (int j = 1; j <= space; j++)
                sb.append(" ");
            }
            if (extraSpace >= i + 1)
              sb.append(" ");
          }
          int sbLen = sb.length();
          for (int i = 1; i <= maxWidth - sbLen; i++)
            sb.append(" ");
          result.add(sb.toString());
          oneLine.clear();
          oneLine.add(words[index]);
          allStrLen = words[index].length();
        }
        index++;
      }

      StringBuilder sb = new StringBuilder();
      int len = 0;
      for (int i = 0; i < oneLine.size() - 1; i++) {
        sb.append(oneLine.get(i));
        sb.append(" ");
        len += oneLine.get(i).length() + 1;
      }
      sb.append(oneLine.get(oneLine.size() - 1));
      len += oneLine.get(oneLine.size() - 1).length();

      for (int i = 1; i <= maxWidth - len; i++)
        sb.append(" ");
      result.add(sb.toString());

      return result;

    }
  }

}
