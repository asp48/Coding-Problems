/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28

Solution: O(min(m,n))
Robot can move only right and down. Let right be represented as 0 and down as 1. 
Observation1:
All paths have a length of m+n-2.
There are (m-1) 0s and (n-1) 1s. Robot is already in location 0. so 1 is deducted.

So the problem reduces to finding (m+n-2)P(m+n-2) / ((m-1)!*(n-1)!)
The division is to ignore repetitions. 

*/

class Solution {
    public int uniquePaths(int m, int n) {
        long result=1;
        int upto = Math.max(m-1, n-1);
        for (int i=(m+n-2); i>upto; i-- ){
            result*=i;
        }
        upto = Math.min(m-1, n-1);
        for (int i=2;i<=upto;i++){
            result/=i;
        }
        return (int)result;
    }
}

/*
Top-Down
Recursion with memorization
O(n^2);
*/
class Solution2{
    public int uniquePaths(int m, int n) {
        long[][] countTable = new long[n][m];
        return (int)findPathCount(0,0,m,n,countTable);
        
    }
    
    public long findPathCount(int i, int j, int m, int n, long[][] countTable){
        if (i==n-1 && j==m-1){
            return 1;
        }
        if (i>=n || j>=m){
            return 0;
        }
        long d=0, r=0;
        if (countTable[i][j] == 0){
            if (i<=n-2){
                d = findPathCount(i+1, j, m, n, countTable);   
            }
            if (j<=m-2){
                r = findPathCount(i, j+1, m, n, countTable);   
            }
            countTable[i][j] = d+r;
        }
        return countTable[i][j];
    }
}

/*
Bottom-up
DP
O(m*n)
*/

class Solution3 {
    public int uniquePaths(int m, int n) {
        long[][] countTable = new long[n][m];
        for (int i=0;i<n;i++){
            countTable[i][m-1] = 1;
        }
        for (int i=0;i<m;i++){
            countTable[n-1][i] = 1;
        }
        
        for(int i=n-2;i>=0;i--){
            for(int j=m-2;j>=0;j--){
                countTable[i][j] = countTable[i+1][j] + countTable[i][j+1];
            }
        }  
        return (int)countTable[0][0];
    }
    
}