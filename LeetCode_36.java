package practice;

import java.util.HashSet;

/**
 *
 * <code>LeetCode Problem 36<code>
 * <p>
 * <li>Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * <li>The Sudoku board could be partially filled, where empty cells are filled with the character
 * '.'.
 * <li>Note:
 * <li>A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells
 * need to be validated.
 * </p>
 * 
 */

public class LeetCode_36 {

  public static void main(String argv[]) {

  }

  /**
   * 这道题看上去简单，就是判断给出的数独是不是合法的，不需要是可解的，只要目前已填上的数字合法就行
   * 
   * 那我们肯定就是要检测 每一行、每一列，每一个小方块内，不能有重复的元素
   * 
   * 判断重复元素我们可以用hashset
   * 
   * 关键在于怎么用最简洁的代码去遍历一个个的小方块
   * 
   * 下面这个解法可以说是很巧妙了
   * 
   * 用/和%去做小方格的遍历
   * 
   * 这道题我就不把那种烂的要死的遍历放上来，直接欣赏美丽的代码
   */
  class Solution {
    public boolean isValidSudoku(char[][] board) {
      for (int i = 0; i < 9; i++) {
        HashSet<Character> rows = new HashSet<Character>();
        HashSet<Character> columns = new HashSet<Character>();
        HashSet<Character> cube = new HashSet<Character>();
        for (int j = 0; j < 9; j++) {
          if (board[i][j] != '.' && !rows.add(board[i][j]))
            return false;
          if (board[j][i] != '.' && !columns.add(board[j][i]))
            return false;
          int RowIndex = 3 * (i / 3);
          int ColIndex = 3 * (i % 3);
          if (board[RowIndex + j / 3][ColIndex + j % 3] != '.'
              && !cube.add(board[RowIndex + j / 3][ColIndex + j % 3]))
            return false;
        }
      }
      return true;
    }
  }
}
