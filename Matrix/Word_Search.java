/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.

Solution: DFS
Time Complexity: Need Reseach
I think its n^2, where n is the length of board.
What if first charcter of string is present at multiple places in the grid. Then we have to apply dfs from each such position.
*/
class Solution {
    public boolean exist(char[][] board, String word) {
        if (board.length*board[0].length < word.length()){
            return false;
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==word.charAt(0)){
                    if(isReachable(board, i, j, word, 0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean isReachable(char [][] board, int i, int j, String word, int k){
        if(i < 0 || i==board.length || j < 0 || j == board[0].length){
            return false;
        }
        if(board[i][j] != word.charAt(k)){
            return false;
        }
        if(k == word.length()-1){
            return true;
        }
        char c = board[i][j];
        board[i][j]='.';
        if(isReachable(board, i, j-1, word, k+1)){
            return true;
        }
        if(isReachable(board, i-1, j, word, k+1)){
            return true;
        }
        if(isReachable(board, i, j+1, word, k+1)){
            return true;
        }
        if(isReachable(board, i+1, j, word, k+1)){
            return true;
        }
        board[i][j]=c;
        return false;
    }
}