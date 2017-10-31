package practice;

/**
 *
 * <code>LeetCode Problem 5<code>
 * <p>
 * <li>Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.</li>
 * <li>Example:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.</li>
 * <li>Example:
 * Input: "cbbd"
 * Output: "bb"</li>
 * </p>
 * 
 */

public class LeetCode_5 {

  public static void main(String argv[]) {
    System.out
        .println(new LeetCode_5().new Solution()
            .longestPalindrome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
  }

  /**
   * 这道题虽然是中等难度，但是要做到不超时，感觉还是比较难的，主要是很难想到那种解法
   * 
   * ok，下面说下我的解题过程
   * 
   * 首先最开始，想到的，当然是找出所有子串，然后一一判断是不是回文串，然而这是N的三次方的时间复杂度
   * 
   * 第二种方式，从头到尾扫一遍字符串，扫到每一个字符时，以该字符为中心点，向两边扩展，找以该字符为中心的最长回文子串（注意奇数偶数串的处理），这样时间复杂度是N的平方
   * 
   * 然而还是超时
   * 
   * 最后，上网看了下，有个叫Manacher的算法，好像是专门处理回文串的算法，时间复杂度为O(N),仔细看了下，确实不错，按照它的原理实现了一遍，就通过了
   * 
   * 参考资料：http://blog.csdn.net/yzl_rex/article/details/7908259
   * 
   * 总的来说，就是，首先要将字符串变为奇数个字符，也就是在每两个字符之间以及首位添加上未出现在字符串中的特殊字符，如#号
   * 
   * 这样，我们找到的回文串也一定是奇数个字符，这样好做对称性分析
   * 
   * 用一个数组，P[n],记录下字符串中，以每个字符为中心的最长回文子串的半径，同时记录下当前已经找到回文子串的最右的边界maxid，以及他对应的中心字符的id
   * 
   * 这样，在计算每个字符为中心的最长回文子串的半径时，我们只需要判断这个中心字符是否在id和maxid和id之间，如果是，那么根据对称性，找到以id为中心和当前字符对称的那个字符的半径值
   * 
   * 但是注意如果那个对称的字符的半径值超过了maxid与当前字符的距离，则只能取他们的最小值。然后继续扩展看是否能增加半径值
   * 
   * 好吧，我觉得我说的不够清楚，因为每有图说明，那你们去看上面的资料吧 ，说的很清楚，但是上面那个网页的代码是有问题的（每有考虑边界问题），代码你可以看我的，我的是正确的
   * 
   * 当然，在用java写的时候一定要注意，在最开始给字符串之间添加#号操作，千万不要用new String()，之后每次+一个字符
   * 
   * 这样效率很慢，会导致超时
   * 
   * 用StringBuilder做字符串连接就没问题了
   *
   */
  class Solution {

    public String longestPalindrome(String s) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
        sb.append("#");
        sb.append(s.charAt(i));
      }
      sb.append("#");

      String ns = sb.toString();

      int p[] = new int[ns.length()];
      int maxid = 0;
      int index = 0;
      int maxLen = 0;
      int ansIndex = -1;

      for (int i = 0; i < ns.length(); i++) {
        if (maxid > i) {
          p[i] = Math.min(p[2 * index - i], maxid - i + 1);
        } else {
          p[i] = 1;
        }

        while ((i + p[i]) < ns.length() && i - p[i] >= 0
            && (ns.charAt(i + p[i]) == ns.charAt(i - p[i]))) {

          p[i]++;
        }

        if (i + p[i] - 1 > maxid) {
          maxid = i + p[i] - 1;
          index = i;
        }


        if (p[i] > maxLen) {
          maxLen = p[i];
          ansIndex = i;
        }
      }

      return ns.substring(ansIndex - maxLen + 1, ansIndex + maxLen - 1).replace("#", "");
    }
  }
}
