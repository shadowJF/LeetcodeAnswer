package practice;

/**
 *
 * <code>LeetCode Problem 79<code>
 * <p>
 * <li>Given a 2D board and a word, find if the word exists in the grid.
 * <li>The word can be constructed from letters of sequentially adjacent cell, where "adjacent"
 * cells are those horizontally or vertically neighboring. The same letter cell may not be used more
 * than once.
 * <li>Example:
 * <li>board =
 * <li>[
 * <li>['A','B','C','E'],
 * <li>['S','F','C','S'],
 * <li>['A','D','E','E']
 * <li>]
 * <li>Given word = "ABCCED", return true.
 * <li>Given word = "SEE", return true.
 * <li>Given word = "ABCB", return false.
 * </p>
 * 
 */

public class LeetCode_79 {

  public static void main(String argv[]) {
    // int[] tmp = {1, 2, 3};
    // System.out.println(new LeetCode_79().new Solution().exist(tmp));
  }

  /**
   * 这道题其实挺简单
   * 
   * 首先，我们遍历矩阵，找是否有word的第一个字符
   * 
   * 如果找到了，我们就从这个字符开始，递归地去找他四周是否能匹配到下一个字符
   * 
   * 但是由于，我们不能找到之前已经找过的字符，所以，我们可能需要一个额外的空间来存某个位置是否已经被找过了
   * 
   * 但是考虑到这个矩阵存的都是letter，所以，当一个位置被考虑过了，直接将它置为*，如果这条路径不行，回溯的时候
   * 
   * 再置回来即可。
   * 
   * 当然我的代码有点冗余，冗长，看看人家第一名的，非常精简 = =
   */
  class Solution {
    public boolean exist(char[][] board, String word) {
      if (board == null || board.length == 0 || board[0].length == 0 || word.length() == 0)
        return false;
      for (int i = 0; i < board.length; i++)
        for (int j = 0; j < board[0].length; j++) {
          if (board[i][j] == word.charAt(0)) {
            char tmp = board[i][j];
            board[i][j] = '*';
            if (foundWord(word, 1, board, i, j))
              return true;
            else
              board[i][j] = tmp;
          }
        }
      return false;
    }

    private boolean foundWord(String word, int index, char[][] board, int i, int j) {
      if (index >= word.length())
        return true;
      if (i - 1 >= 0 && board[i - 1][j] == word.charAt(index)) {
        char tmp = board[i - 1][j];
        board[i - 1][j] = '*';
        if (foundWord(word, index + 1, board, i - 1, j))
          return true;
        else
          board[i - 1][j] = tmp;
      }

      if (i + 1 < board.length && board[i + 1][j] == word.charAt(index)) {
        char tmp = board[i + 1][j];
        board[i + 1][j] = '*';
        if (foundWord(word, index + 1, board, i + 1, j))
          return true;
        else
          board[i + 1][j] = tmp;
      }

      if (j - 1 >= 0 && board[i][j - 1] == word.charAt(index)) {
        char tmp = board[i][j - 1];
        board[i][j - 1] = '*';
        if (foundWord(word, index + 1, board, i, j - 1))
          return true;
        else
          board[i][j - 1] = tmp;
      }

      if (j + 1 < board[0].length && board[i][j + 1] == word.charAt(index)) {
        char tmp = board[i][j + 1];
        board[i][j + 1] = '*';
        if (foundWord(word, index + 1, board, i, j + 1))
          return true;
        else
          board[i][j + 1] = tmp;
      }
      return false;
    }

  }

}
