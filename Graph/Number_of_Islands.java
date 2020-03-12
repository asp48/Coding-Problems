/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3

Solution: 
DFS
time: O(M*N)
space: O(1) if you are allowed to change the input matrix, else O(M*N)
*/
class Solution {
    public int numIslands(char[][] grid) {
        int islands=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    dfs(grid, i,j);
                    islands++;
                }
            }
        }
        return islands;
    }
    
    public void dfs(char[][] grid, int i, int j){
        if(i>=grid.length || i<0 || j<0 || j >=grid[0].length){
            return;
        }
        //Not an island, or it is already visited
        if(grid[i][j]=='0'){
            return;
        }
        //Resetting to 0 so that this location will not be processed again. In other words, marking it as visited.
        grid[i][j]='0'; 
        //Apply DFS in all four directions
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
    }
}