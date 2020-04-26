/*
https://leetcode.com/problems/valid-sudoku/

Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true

All solutions are constant space.
Time: O(m*n) = O(9*9) = O(1)
*/
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i=0;i<9;i++){
            Set<Character> cset = new HashSet<>(), rset = new HashSet<>(), bset = new HashSet<>();
            for(int j=0;j<9;j++){
                if(board[i][j]!='.' && !rset.add(board[i][j])) return false;
                if(board[j][i]!='.' && !cset.add(board[j][i])) return false;

                int r = (i/3)*3 + j/3, c = (i%3)*3 + j%3;
                if(board[r][c]!='.' && !bset.add(board[r][c])) return false;
            }
        }
        return true;
    }
}

/*
Use string description to create unique combination.
Instead of storing in a separate array like row[i][number] = true, we can store as a string "number in row i" into a common Set.
*/
class Solution2 {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                char num = board[i][j];
                if(num!='.' && (
                   !seen.add(num + " in row " + i) ||
                   !seen.add(num + " in column " + j) ||
                   !seen.add(num + " in block " + i/3 + " " + j/3))
                ){
                    return false;
                }
            }
        }
        return true;
    }
}

/*
9 bits can be used to represent each number.
If a bit is 1, it implies that corresponding number already exists, else if the bit is 0, then the number does not exist.
*/
class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 3 arrays to hold 3 combinations
        int[] row = new int[9], col = new int[9], grid = new int[9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    continue;
                }
                // shift the 1-bit to the right for current number(which is board[i][j]) of times
                int mask = 1 << (board[i][j] - '0');
                // check if the bit at that position is 1 in each combination
                if((mask & row[i]) > 0 || (mask & col[j]) > 0 || (mask & grid[(i/3)*3 + j/3]) > 0){
                    // > 0 implies, bit is 1, so the number already exists
                    return false;
                }
                // set the bit to 1 for the current number in each combination
                row[i] |= mask;
                col[j] |= mask;
                grid[(i/3)*3 + j/3] |= mask;
            }
        }
        return true;
    }
}