/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4

DP
time: O(M*N)
space: O(M*N)
At each point where element=1, the decision is whether current 1 will contribut to square or not. 
We create a 2D matrix called dp, in which dp[i][j] stores the length a square possible by including input[i][j]. Note that it is not the max length possible upto i*j. It is a local optimal and the result is the max of all the local optimals.
DP matrix is updated as follows
At i,j and input[i][j]==1, 
    1. If its left, diagonal and top are same, then it is a square of size 1+(left or diagonal or top)
    2. If not same, then it is a square of size 1 + min(left, diagonal, top);
    3. If one of them is zero, then the maximum square that can be formed is of length 1, i.e square containing only current element.

In the following solution dp[i][j] stores the length of the square upto i-1*j-1 and loop starts directly from i=1, j=1. This is to avoid two extra for loops to initialize first row and first column.
*/
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if(m==0) return 0;
        int n = matrix[0].length;
        int[][] dp = new int[m+1][n+1];
        int max=0;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(matrix[i-1][j-1]=='1'){
                    dp[i][j] = 1+min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]);
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max*max;
    }
    
    public int min(int a, int b, int c){
        return (a<b)?(a<c?a:c):(b<c?b:c);
    }
}