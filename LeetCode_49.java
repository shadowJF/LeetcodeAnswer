package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <code>LeetCode Problem 47<code>
 * <p>
 * <li>Given an array of strings, group anagrams together.
 * <li>For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * <li>Return:
 * <li>[
 * <li>["ate", "eat","tea"],
 * <li>["nat","tan"],
 * <li>["bat"]
 * <li>]
 * </p>
 * 
 */

public class LeetCode_49 {

  public static void main(String argv[]) {
    String[] tmp = {"ate", "aet"};
    new LeetCode_49().new Solution().groupAnagrams(tmp);
  }

  /**
   * 这题我想到的方法就是最笨的，但是没想到大家都是这么做的。。
   * 
   * 对每个字符串，变成char的数组，然后排序，重新组成新的字符串，作为key
   * 
   * 这样所有相同的key的字符放到一个list中，最后将所有list取出来放到result中即可
   * 
   * = =
   */
  class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
      List<List<String>> result = new ArrayList<List<String>>();
      Map<String, List<String>> map = new HashMap<String, List<String>>();

      for (String s : strs) {
        List<Character> l = new ArrayList<Character>();
        for (int i = 0; i < s.length(); i++) {
          l.add(s.charAt(i));
        }
        Collections.sort(l);
        StringBuilder sb = new StringBuilder();
        l.forEach(c -> {
          sb.append(c);
        });

        List<String> list = map.getOrDefault(sb.toString(), new ArrayList<String>());
        list.add(s);
        map.put(sb.toString(), list);

      }
      for (String s : map.keySet()) {
        result.add(map.get(s));
      }
      return result;
    }
  }
}
