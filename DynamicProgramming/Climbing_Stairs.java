/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Solution:
Uses ncr-> fails significantly for large inputs
can be optimized using binomial coefficients, but not significantly.
*/
class Solution {
    public int climbStairs(int n) {
        long count=0;
        // find number of ones and number of twos, then find number of distinct ways using ncr
        for(int i=0;i<=n;i++){
            int twos = (n-i)%2;
            if(twos==0){
                count += ncr((n-i)/2, i);
            } 
        }
        return (int)count;
    }
    // (m+n)! / m! * n!   to avoid repetitions
    public long ncr(int m, int n){
        long result = 1;
        int upto = Math.max(m,n);
        for(int i=m+n;i>upto;i--){
            result *= i;
        }
        upto = Math.min(m,n);
        for(int i=2;i<=upto;i++){
            result /= i;
        }
        return result;
    }
}

/*
Top-Down
Backtracking 
*/
class Solution2 {
    public int climbStairs(int n) {
        return getClimbStairWays(0, n);
    }
    
    public int getClimbStairWays(int i, int n){
        if (i>n){
            return 0;
        }
        if (i==n||i==n-1){
            return 1;
        }
        return getClimbStairWays(i+1, n) + getClimbStairWays(i+2, n);
    }
}

/*
Top-Down
Backtracking with memorization
*/
class Solution3 {
    public int climbStairs(int n) {
        long table[] = new long[n+1];
        return (int)getClimbStairWays(0, n, table);
    }
    
    public long getClimbStairWays(int i, int n, long[] table){
        if (i>n){
            return 0;
        }
        if (i==n||i==n-1){
            return 1;
        }
        long ones = table[i+1]==0? getClimbStairWays(i+1, n, table): table[i+1];
        long twos = table[i+2]==0? getClimbStairWays(i+2, n, table): table[i+2];
        table[i] = ones + twos;
        return table[i];
    }
}

/*
Dp
Bottom-UP
O(n)
*/
class Solution4 {
    public int climbStairs(int n) {
        if (n==1) return 1;
        long table[] = new long[n+1];
        table[n-1] = 1;
        table[n-2] = 2;
        for(int i=n-3;i>=0;i--){
            table[i] = table[i+1] + table[i+2];
        }
        return (int) table[0];
    }
}