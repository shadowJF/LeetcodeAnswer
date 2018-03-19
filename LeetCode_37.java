package practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * <code>LeetCode Problem 36<code>
 * <p>
 * <li>Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <li>Empty cells are indicated by the character '.'.
 * <li>You may assume that there will be only one unique solution.
 * </p>
 * 
 */

public class LeetCode_37 {

  public static void main(String argv[]) {
    char[][] a = {{'.', '.', '9', '7', '4', '8', '.', '.', '.'},
        {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
        {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
        {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
        {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
        {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
        {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};
    new LeetCode_37().new Solution().solveSudoku(a);

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        System.out.print(a[i][j]);
        System.out.print(" ");
      }
      System.out.println("");
    }
  }


  class Solution {
    public void solveSudoku(char[][] board) {
      Map<Integer, HashSet<Character>> rowMap = new HashMap<Integer, HashSet<Character>>();
      Map<Integer, HashSet<Character>> colMap = new HashMap<Integer, HashSet<Character>>();
      Map<Integer, HashSet<Character>> matrixMap = new HashMap<Integer, HashSet<Character>>();
      for (int i = 0; i < 9; i++) {
        rowMap.put(i, new HashSet<Character>());
        colMap.put(i, new HashSet<Character>());
        matrixMap.put(i, new HashSet<Character>());
      }

      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          if (board[i][j] != '.') {
            int matrixIndex = 3 * (i / 3) + (j / 3);
            rowMap.get(i).add(board[i][j]);
            colMap.get(j).add(board[i][j]);
            matrixMap.get(matrixIndex).add(board[i][j]);
          }
        }
      }

      solve(rowMap, colMap, matrixMap, board, 0, 0);
    }

    private boolean solve(Map<Integer, HashSet<Character>> rowMap,
        Map<Integer, HashSet<Character>> colMap, Map<Integer, HashSet<Character>> matrixMap,
        char[][] board, int r, int c) {

      if (c == 9) {
        r += 1;
        c = 0;
      }

      if (r >= 9) {
        return true;
      }

      if (board[r][c] != '.') {
        return solve(rowMap, colMap, matrixMap, board, r, c + 1);
      } else {
        for (int i = 1; i <= 9; i++) {
          int matrixIndex = 3 * (r / 3) + (c / 3);
          Character ch = String.valueOf(i).charAt(0);
          if (rowMap.get(r).contains(ch) || colMap.get(c).contains(ch)
              || matrixMap.get(matrixIndex).contains(ch)) {
            continue;
          } else {
            board[r][c] = ch;
            rowMap.get(r).add(ch);
            colMap.get(c).add(ch);
            matrixMap.get(matrixIndex).add(ch);
            if (solve(rowMap, colMap, matrixMap, board, r, c + 1)) {
              return true;
            } else {
              board[r][c] = '.';
              rowMap.get(r).remove(ch);
              colMap.get(c).remove(ch);
              matrixMap.get(matrixIndex).remove(ch);
            }
          }
        }
      }

      return false;
    }
  }
}
