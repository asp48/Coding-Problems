/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.

Dynamic Programming
time: O(n)
Intuition:
While traversing, at each point i, the decision is whether to 
    1. add the current element to the max sum calculated upto i-2 (cannot be added to i-1 as per the problem) or 
    2. ignore the current element and choose the previous max.
*/
// Space : O(n)
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        int[] dp = new int[n+1];
        dp[0] = nums[0];
        if(n>1){
            dp[1] = Math.max(dp[0], nums[1]);
        }
        for(int i=2;i<n;i++){
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
        }
        return dp[n-1];
    }
}
//Compact Version of solution above
class Solution1 {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i=2;i<=n;i++){
            dp[i] = Math.max(dp[i-1], nums[i-1] + dp[i-2]);
        }
        return dp[n];
    }
}
// Space O(1)
class Solution2 {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        int s1=0;
        int s2=nums[0];
        int tmp;
        for(int i=1;i<n;i++){
            if(nums[i]+s1 > s2){
                tmp = s2;
                s2 = nums[i]+s1;
                s1 = tmp;
            } else{
                s1 = s2;
            }
        }
        return s2;
    }
}
//Compact Version of solution2
class Solution3 {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        int s1=0;
        int s2=nums[0];
        int tmp;
        for(int i=1;i<n;i++){
            tmp = Math.max(s1+nums[i], s2);
            s1 = s2;
            s2 = tmp;
        }
        return s2;
    }
}
/*
Part-2
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
*/

class Solution4 {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        if(n==1) return nums[0];
        return Math.max(helper(nums,0,n-2), helper(nums,1,n-1));
    }
    
    public int helper(int[] nums, int low, int high){
        int s1=0;
        int s2=nums[low];
        int tmp;
        for(int i=low+1;i<=high;i++){
            tmp = Math.max(s1+nums[i], s2);
            s1 = s2;
            s2 = tmp;
        }
        return s2;
    }
}