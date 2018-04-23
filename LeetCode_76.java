package practice;

import java.util.HashMap;

/**
 *
 * <code>LeetCode Problem 76<code>
 * <p>
 * <li>Given a string S and a string T, find the minimum window in S which will contain all the
 * characters in T in complexity O(n).
 * <li>Example:
 * <li>Input: S = "ADOBECODEBANC", T = "ABC"
 * <li>Output: "BANC"
 * <li>Note:
 * <li>If there is no such window in S that covers all characters in T, return the empty string "".
 * <li>You are guaranteed that there will always be only one unique minimum window in S.
 * </p>
 * 
 */

public class LeetCode_76 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_76().new Solution().minWindow("a", "aa"));
  }

  /**
   * 这道题又是字符串的问题，在这道题的讨论中，有人已经发明了一套针对这种字符串问题的通用件解法
   * 
   * 我就不介绍了，你们自己去看，
   * 
   * 我这里的解法跟他的思路是一样
   * 
   * 首先，说明一下这道题，是要考虑到字符的个数的，也就是说如果要包含的字符串中有两个a，那么你找到的这个串里也必须至少有两个a
   * 
   * 那么我们用两个hashmap来存，第一个tmap存待包含的字符串中的每个字符的个数
   * 
   * 第二个map来存我当前找到的每个字符的个数
   * 
   * 我设计两个指针，一个left，一个right
   * 
   * 首先，让right从头向尾遍历，每遇到一个tmap中的字符，就相应地在map中增加他的值
   * 
   * 直到找到了包含了整个字符串t的位置，也就是map中的每个字符的个数都大于等于该字符在tmap中的值了
   * 
   * 那么，这时我们就要移动left指针了，left要移动到哪呢？
   * 
   * 移动到一个在t中的字符的位置，且该字符在map中的value等于tmap中的value，这样，left-right之间的字符串就是我们要找的一个区间
   * 
   * 为什么left要移动到这样一个位置，因为为了保证该字符串要包含整个t字符串，那么map中每个字符的value必须大于等于tmap中字符的value，如果再右移
   * 
   * 会导致某个字符数量小于要求的数量，而同时为了保证找到的这个区间是最小的，所以那些不属于t中的字符在left右移时都不需要考虑
   * 
   * 这样循环下去就得到我们的解了
   */
  class Solution {
    public String minWindow(String s, String t) {
      HashMap<Character, Integer> tmap = new HashMap<Character, Integer>();
      HashMap<Character, Integer> map = new HashMap<Character, Integer>();
      for (int i = 0; i < t.length(); i++) {
        tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0) + 1);
      }

      String min = "";

      int left = 0;
      int right = 0;

      while (right < s.length()) {
        if (tmap.containsKey(s.charAt(right))) {
          map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);
          if (found(map, tmap)) {
            while (!tmap.containsKey(s.charAt(left))
                || (map.get(s.charAt(left)) > tmap.get(s.charAt(left)))) {
              if (tmap.containsKey(s.charAt(left))
                  && map.get(s.charAt(left)) > tmap.get(s.charAt(left)))
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
              left++;
            }
            String str = s.substring(left, right + 1);
            if (min.equals("") || str.length() < min.length())
              min = str;
          }
        }
        right++;
      }
      return min;
    }

    private boolean found(HashMap<Character, Integer> map, HashMap<Character, Integer> tmap) {
      if (map.size() == tmap.size()) {
        for (Character key : tmap.keySet()) {
          if (map.get(key) < tmap.get(key))
            return false;
        }
        return true;
      } else
        return false;
    }
  }

}
