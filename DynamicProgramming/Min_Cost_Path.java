/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

Solution: Top-Bottom
Backtracking with memorization
O(m*n)
*/
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long[][] table = new long[m][n];
        return (int) getMinPathSum(grid, 0,0,m,n, table);
    }
    
    public long getMinPathSum(int[][] grid, int i, int j, int m, int n, long[][] table){
        if(i==m-1 && j==n-1) return grid[i][j];
        long d=Long.MAX_VALUE, r=Long.MAX_VALUE;
        if (i>=m || j>=n){
            return Long.MAX_VALUE;
        }
        if (table[i][j]==0){
            if(i<=m-2){
                d = getMinPathSum(grid, i+1, j, m, n, table);
            }
            if(j<=n-2){
                r = getMinPathSum(grid, i, j+1, m, n, table);
            }
            table[i][j] = grid[i][j] + Math.min(d,r);
        }
        return table[i][j];
    }
}

/*
Bottom-Up Solution
DP
*/
class Solution2 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long[][] table = new long[m][n];
        table[m-1][n-1] = grid[m-1][n-1];
        for(int i=m-2;i>=0;i--){
            table[i][n-1] = grid[i][n-1] + table[i+1][n-1];
        }
        for(int i=n-2;i>=0;i--){
            table[m-1][i] = grid[m-1][i] + table[m-1][i+1];
        }
        
        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--){
                table[i][j] = grid[i][j] + Math.min(table[i+1][j], table[i][j+1]);
            }
        }
        
        return (int) table[0][0];
    }
}