/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
*/
/*
Recursion with Memorization, top-bottom
time: O(sqrtn)
space: O(n)

1. Store all the perfect squares upto n in an array[square root of n].
2. Recurse the array from right, deciding the following at each point,
2.1.     if curr element equals n, return 1 as count
2.2.     if curr element > n, skip the current element and recurse starting from next.
2.3.     if n is divisible by current element, return the quotient as count
2.4.     Else, Return the minimum of recursing by skipping the current element and including the current element.

10ms faster than 95%
*/
class Solution {
    public int numSquares(int n) {
        if(n==0) return 0;
        int sqrtn = (int)Math.sqrt(n);
        int[] psquares = new int[sqrtn+1];
        //Step 1
        for(int i=1;i<=sqrtn;i++){
            psquares[i] = i*i;
        }
        int[] memo = new int[n+1];
        //Step 2
        return findCount(psquares, n, sqrtn, memo);
    }
    
    public int findCount(int[] psquares, int n, int i, int[] memo)
    {
        if(memo[n]!=0){
            return memo[n];
        }
        int curr = psquares[i];
        //Step 2.1
        if(curr == n){
            return 1;
        }
        //Step 2.2
        if(curr > n){
            memo[n] = findCount(psquares, n, i-1, memo);
            return memo[n];
        }
        int rem = n % curr;
        int factor = n/curr;
        //Step 2.3
        if(rem == 0){
            memo[n] = factor;
        //Step 2.4
        } else if(i>1){
            memo[n] = Math.min(factor + findCount(psquares, rem, i-1, memo), findCount(psquares, n, i-1, memo));
        } else{
        //O is not a perfect square and should not be included in the count. 
            memo[n] = Integer.MAX_VALUE;
        }
        return memo[n];
    }
}

/*
DP, bottom-up
time: O(n^2)
space: O(n)

19ms faster than 89%
*/
class Solution2 {
    public int numSquares(int n) {
        if(n==0) return 0;
        int[] dp = new int[n+1];
        
        for(int i=1;i<=n;i++){
            int min = i;
            for(int j=2;j*j<=i;j++){
                int ps = j*j;
                min = Math.min(1 + dp[i-ps], min);
            }
            dp[i] = min;
        }
        return dp[n];
    }
}

/*
ToDo: Explore other faster approaches to solve this.
Lagrange's Algorithm is a mathematical theorme which can be used to solve this problem and is the optimal solution.
*/