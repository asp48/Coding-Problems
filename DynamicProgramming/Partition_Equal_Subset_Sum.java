/*
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.
 
Example 1:
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

This problem is a twisted version of subset sum problem.
The task is to find a subset with sum (totalSum/2)
If totalSum is odd, then the array cannot be partitioned into two subsets with equal sum. So in such cases, we can directly return result as false.
*/
/*
Bottom-Up Recursion with memorization
time: O(n*targetSum)
space: O(n*targetSum)
*/
class Solution {
    public boolean canPartition(int[] nums) {
        int total=0;
        for(int num: nums){
            total += num;
        }
        if(total%2 != 0) return false;
        int targetSum = total/2;
        int[][] memo = new int[nums.length][targetSum+1];
        return helper(nums, 0, 0, targetSum, memo);
    }
    
    public boolean helper(int[] nums, int i, int curSum, int targetSum, int[][] memo){
        if(curSum==targetSum){
            return true;
        } else if(i==nums.length || curSum>targetSum){
            return false;
        }
        if(memo[i][curSum]==1) return true;
        if(memo[i][curSum]==2) return false;
        boolean r = helper(nums, i+1, curSum+nums[i], targetSum, memo)|| helper(nums, i+1, curSum, targetSum, memo);
        memo[i][curSum] = (r)?1:2;
        return r;
    }
}

/*
Top-Down DP
*/
class Solution2 {
    public boolean canPartition(int[] nums) {
        int total=0, n=nums.length;
        for(int num: nums){
            total += num;
        }
        if(total%2 != 0) return false;
        int targetSum = total/2;
        boolean[][] dp = new boolean[n+1][targetSum+1];
        for(int i=n-1;i>=0;i--){
            for(int j=targetSum;j>=0;j--){
                if(j==targetSum){
                    dp[i][j] = true;
                    continue;
                }
                if(j+nums[i]<=targetSum){
                    dp[i][j] = dp[i+1][j+nums[i]];
                }
                dp[i][j] = dp[i][j] || dp[i+1][j];
            }
        }
        return dp[0][0];
    }
}

/*
Top-Down Recursion with memorization
*/
class Solution3 {
    public boolean canPartition(int[] nums) {
        int total=0, n=nums.length;
        for(int num: nums){
            total += num;
        }
        if(total%2 != 0) return false;
        int targetSum = total/2;
        int[][] memo = new int[n+1][targetSum+1];
        return helper(nums, n, 0, targetSum, memo);
    }
    
    public boolean helper(int[] nums, int i, int curSum, int targetSum, int[][] memo){
        if(curSum==targetSum){
            return true;
        } else if(i==0 || curSum>targetSum){
            return false;
        }
        if(memo[i][curSum]==1) return true;
        if(memo[i][curSum]==2) return false;
        boolean r = helper(nums, i-1, curSum+nums[i-1], targetSum, memo)|| helper(nums, i-1, curSum, targetSum, memo);
        memo[i][curSum] = (r==true)?1:2;
        return r;
    }
}

/*
Bottom-Up DP
*/
class Solution4 {
    public boolean canPartition(int[] nums) {
        int total=0, n=nums.length;
        for(int num: nums){
            total += num;
        }
        if(total%2 != 0) return false;
        int targetSum = total/2;
        boolean[][] dp = new boolean[n+1][targetSum+1];
        for(int i=1;i<=n;i++){
            for(int j=0;j<=targetSum;j++){
                if(j==targetSum){
                    dp[i][j] = true;
                    continue;
                }
                if(j+nums[i-1]<=targetSum){
                    dp[i][j] = dp[i-1][j+nums[i-1]];
                }
                dp[i][j] = dp[i][j] || dp[i-1][j];
            }
        }
        return dp[n][0];
    }
}

/*
Space Optimization: O(2*targetSum)
Since only previous row is required during computation of dp, we can optimize the solution to use only two rows.
Reference: https://www.geeksforgeeks.org/subset-sum-problem-osum-space/
*/
class Solution5 {
    public boolean canPartition(int[] nums) {
        int total=0, n=nums.length;
        for(int num: nums){
            total += num;
        }
        if(total%2 != 0) return false;
        int targetSum = total/2;
        boolean[][] dp = new boolean[2][targetSum+1];
        for(int i=1;i<=n;i++){
            for(int j=0;j<=targetSum;j++){
                if(j==targetSum){
                    dp[i%2][j] = true;
                    continue;
                }
                if(j+nums[i-1]<=targetSum){
                    dp[i%2][j] = dp[(i+1)%2][j+nums[i-1]];
                }
                dp[i%2][j] = dp[i%2][j] || dp[(i+1)%2][j];
            }
        }
        return dp[n%2][0];
    }
}