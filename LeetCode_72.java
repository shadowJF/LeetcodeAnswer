package practice;

/**
 *
 * <code>LeetCode Problem 72<code>
 * <p>
 * <li>Given two words word1 and word2, find the minimum number of steps required to convert word1
 * to word2. (each operation is counted as 1 step.)
 * <li>You have the following 3 operations permitted on a word:
 * <li>a) Insert a character
 * <li>b) Delete a character
 * <li>c) Replace a character
 * </p>
 * 
 */

public class LeetCode_72 {

  public static void main(String argv[]) {
    // // String[] tmp = {"Listen", "to", "many,", "speak", "to", "a", "few."};
    System.out.println(new LeetCode_72().new Solution().minDistance("/home/foo/./bar/", ""));
  }

  /**
   * DP经典问题之一
   * 
   * 我们用md[i][j]表示word1的前i个字符与word2的前j个字符的编辑距离
   * 
   * 那么md[i][j] = min(md[i-1][j]+1 , md[i][j-1]+1,md[i-1][j-1]+(word1[i]==word2[j]?0:1))
   * 
   * 同时，md[0][0]=0
   * 
   * 当i=0时，md[0][j] = j;
   * 
   * 当j=0时，md[i][0] = i;
   */
  class Solution {
    public int minDistance(String word1, String word2) {
      int[][] md = new int[word1.length() + 1][word2.length() + 1];
      md[0][0] = 0;
      for (int i = 1; i <= word1.length(); i++)
        md[i][0] = i;
      for (int i = 1; i <= word2.length(); i++)
        md[0][i] = i;
      for (int i = 1; i <= word1.length(); i++)
        for (int j = 1; j <= word2.length(); j++)
          md[i][j] =
              Math.min(md[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1),
                  Math.min(md[i - 1][j] + 1, md[i][j - 1] + 1));
      return md[word1.length()][word2.length()];
    }
  }

}
