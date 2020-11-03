/*
https://leetcode.com/problems/jump-game-ii/
Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

You can assume that you can always reach the last index.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

*/

class Solution {
    public int jump(int[] nums) {
        //return topDownDp(nums, 0, new Integer[nums.length]);
        //return arrayDp(nums);
        return linearSoln(nums);
    }
    
    public int topDownDp(int[] nums, int pos, Integer[] dp){
        if(pos==nums.length-1){
            return 0;
        }
        if(pos + nums[pos]>=nums.length-1){
            return 1;
        }
        if(dp[pos]!=null){
            return dp[pos];
        }
        int min = nums.length+1;
        for(int i=1;i<=nums[pos];i++){
            int steps = topDownDp(nums, pos+i, dp);
            min = Math.min(min, steps+1);
        }
        dp[pos] = min;
        return min;
    }
    
    public int arrayDp(int[] nums){
        int[] dp = new int[nums.length];
        dp[nums.length-1]=0;
        for(int i=nums.length-2;i>=0;i--){
            if(i+nums[i]>=nums.length-1){
                dp[i] = 1;
            } else {
                int min = nums.length;
                for(int j=1;j<=nums[i];j++){
                    min = Math.min(min, dp[i+j]+1);
                }
                dp[i] = min;
            }
        }
        return dp[0];
    }

/*
The above two solutions use dfs and can be slow.
The below solution uses BFS technique.
Time: O(n)
Space: O(1)
Good Explaination:
https://www.youtube.com/watch?v=vBdo7wtwlXs
*/    
    public int linearSoln(int[] nums){
        if(nums.length<=1){
            return 0;
        }
        int curEnd = 0;
        int curFarthest=0;
        int jumps = 0;
        for(int i=0;i<nums.length-1;i++){
            // max reachable from the current point
            curFarthest = Math.max(curFarthest, i+nums[i]);
            // exit if we have reached the end
            if(curFarthest>=nums.length-1){
                jumps++;
                break;
            }
            // reached the end of max so far, choose the next farthest point.
            if(i==curEnd){
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }
}
