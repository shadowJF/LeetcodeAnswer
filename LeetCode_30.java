package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <code>LeetCode Problem 29<code>
 * <p>
 * <li>You are given a string, s, and a list of words, words, that are all of the same length. Find
 * all starting indices of substring(s) in s that is a concatenation of each word in words exactly
 * once and without any intervening characters.
 * <li>For example, given:
 * <li>s: "barfoothefoobarman"
 * <li>words: ["foo", "bar"]
 * <li>You should return the indices: [0,9].
 * <li>(order does not matter).
 * </p>
 * 
 */

public class LeetCode_30 {

  public static void main(String argv[]) {
    String[] tmp = {"ab", "ba", "ba"};
    System.out.println(new LeetCode_30().new Solution().findSubstring3("ababaab", tmp));
  }

  /**
   * 这道题还是蛮有难度的，很容易超时
   * 
   * 我这里提供的两个思路，第一个超时了，第二个150多ms，但是accept
   * 
   * 其实看discussion里面，比较流行的解法是slide windows的方法
   * 
   * 即设置左右两个pointer，移动右指针，直到所有的
   */
  public class Solution {

    /**
     * 第一种思路，很原始，先将数组进行排序
     * 
     * 然后从s中遍历每一个字符，以该字符为开头，取words.length个字符串，然后再排序
     * 
     * 比较这两个数组是否相等
     * 
     * 时间复杂度为n*k*(logk + m)
     * 
     * 理所当然的超时了
     */
    public List<Integer> findSubstring(String s, String[] words) {
      List<Integer> result = new ArrayList<Integer>();
      if (words == null || words.length == 0)
        return result;

      int size = words.length;
      int wlen = words[0].length();
      int len = size * wlen;

      Arrays.sort(words);

      for (int i = 0; i < s.length() - len + 1; i++) {
        String[] ws = new String[size];
        for (int j = 0; j < size; j++) {
          ws[j] = s.substring(i + j * wlen, i + j * wlen + wlen);
        }
        Arrays.sort(ws);

        int k;
        for (k = 0; k < size; k++) {
          if (!words[k].equals(ws[k]))
            break;
        }

        if (k == size)
          result.add(i);
      }

      return result;

    }

    /**
     * 第二种思路虽然也简单，但是不会超时
     * 
     * 首先将words中的字符串都放到一个map中，并且value为该word出现的次数
     * 
     * 接着还是遍历s
     * 
     * 以当前字符为头，依次去k个字符串，并且新建立一个curmap，如果该字符串不在map中
     * 
     * 说明不匹配，换s中的下一个字符
     * 
     * 如果该字符串在map中，则对curmap中的count进行更新，并且看看curmap中的值是否大于map中的值
     * 
     * 如果大了，那么也不匹配，换s中的下一个字符
     * 
     * 这个的时间复杂度为n*k*m(因为substring的时间复杂度为m)
     */
    public List<Integer> findSubstring2(String s, String[] words) {
      List<Integer> result = new ArrayList<Integer>();

      if (words == null || words.length == 0)
        return null;

      Map<String, Integer> map = new HashMap<String, Integer>(words.length);
      for (int i = 0; i < words.length; i++)
        map.put(words[i], map.getOrDefault(words[i], 0) + 1);

      int N = s.length();
      int M = words[0].length();
      int K = words.length;

      for (int i = 0; i < N - M * K + 1; i++) {
        Map<String, Integer> curMap = new HashMap<String, Integer>(K);

        int j;

        for (j = 0; j < K; j++) {
          String tmp = s.substring(i + j * M, i + (j + 1) * M);

          if (map.containsKey(tmp)) {
            int curNum = curMap.getOrDefault(tmp, 0) + 1;
            if (curNum > map.get(tmp))
              break;
            else
              curMap.put(tmp, curNum);
          } else {
            curMap.clear();
            break;
          }
        }

        if (j == K) {
          result.add(i);
        }

      }

      return result;

    }

    public List<Integer> findSubstring3(String s, String[] words) {
      if (words == null || words.length == 0)
        return null;
      List<Integer> result = new ArrayList<Integer>(s.length());

      Map<String, Integer> map = new HashMap<String, Integer>(words.length);

      for (int i = 0; i < words.length; i++)
        map.put(words[i], map.getOrDefault(words[i], 0) + 1);

      int wordCount = map.size();

      int N = s.length();
      int M = words[0].length();
      int K = words.length;


      for (int i = 0; i < M; i++) {

        int left = i;
        int right = i;
        Map<String, Integer> curMap = new HashMap<String, Integer>();
        int fail = wordCount;

        while (right < N - M + 1) {

          while (fail > 0 && right < N - M + 1) {
            String rs = s.substring(right, right + M);
            if (map.containsKey(rs)) {
              curMap.put(rs, curMap.getOrDefault(rs, 0) + 1);
              if (curMap.get(rs).equals(map.get(rs))) {
                fail--;
              }
            }
            right += M;
          }

          while (fail == 0 && left < right) {
            String ls = s.substring(left, left + M);
            if (map.containsKey(ls)) {
              curMap.put(ls, curMap.get(ls) - 1);
              if (curMap.get(ls).equals(map.get(ls) - 1)) {
                if ((right - left) / M == K) {
                  result.add(left);
                }
                fail++;
              }
            }
            left += M;
          }
        }
      }

      return result;

    }
  }

}
