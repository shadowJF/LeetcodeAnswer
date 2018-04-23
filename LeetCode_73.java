package practice;

/**
 *
 * <code>LeetCode Problem 73<code>
 * <p>
 * <li>Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * <li>click to show follow up.
 * <li>Follow up:
 * <li>Did you use extra space?
 * <li>A straight forward solution using O(mn) space is probably a bad idea.
 * <li>A simple improvement uses O(m + n) space, but still not the best solution.
 * <li>Could you devise a constant space solution?
 * </p>
 * 
 */

public class LeetCode_73 {

  public static void main(String argv[]) {
    // // String[] tmp = {"Listen", "to", "many,", "speak", "to", "a", "few."};
    // System.out.println(new LeetCode_73().new Solution().setZeroes("/home/foo/./bar/", ""));
  }

  /**
   * 这道题要求用常量空间复杂度，一般的想法是记录为0的行和列
   * 
   * 但那样空间复杂度为O(M+N)
   * 
   * 所以，我们其实可以用第一行和第一列的数来记录每一行和每一列的状态
   * 
   * 如果这一行要被标记为0，则令行首的元素为0
   * 
   * 如果这一列要被标记为0，则令列首的元素为0，
   * 
   * 但这样，第一行第一列的元素既被用来标记第一行也被用来标记第一列， 因此我们需要一个额外的元素来标记第一列，
   * 
   * 而matrix[0][0]默认用来标记第一行就行了
   * 
   * 当然我这里，为了理解方便，直接用两个额外空间，分别来表示第一行和第一列是否为0
   */
  class Solution {
    public void setZeroes(int[][] matrix) {
      boolean firstColZero = false;
      boolean firstRowZero = false;
      for (int i = 0; i < matrix.length; i++)
        for (int j = 0; j < matrix[0].length; j++) {
          if (matrix[i][j] == 0) {
            matrix[i][0] = 0;
            matrix[0][j] = 0;
            if (j == 0)
              firstColZero = true;
            if (i == 0)
              firstRowZero = true;
          }
        }
      for (int i = 1; i < matrix.length; i++)
        if (matrix[i][0] == 0)
          for (int j = 1; j < matrix[0].length; j++)
            matrix[i][j] = 0;
      for (int i = 1; i < matrix[0].length; i++)
        if (matrix[0][i] == 0)
          for (int j = 1; j < matrix.length; j++)
            matrix[j][i] = 0;
      if (firstColZero)
        for (int i = 0; i < matrix.length; i++)
          matrix[i][0] = 0;
      if (firstRowZero)
        for (int i = 0; i < matrix[0].length; i++)
          matrix[0][i] = 0;
    }
  }

}
