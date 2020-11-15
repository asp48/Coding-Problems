/*
https://leetcode.com/problems/wiggle-subsequence/

A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.
For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.
Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

Example 1:
Input: [1,7,4,9,2,5]
Output: 6
Explanation: The entire sequence is a wiggle sequence.
Example 2:

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
Example 3:

Input: [1,2,3,4,5,6,7,8,9]
Output: 2
*/

/*
Solution similar to Longest_Increasing_Subsequence.
up[i] = length of longest wiggle subsequence ending with a positive slope or difference.
down[i] = length of longest wiggle subsequence ending with a negative difference.
ans = max(up[i], slow[i]) for 1=0 to n
Time: O(n^2)
Space: O(n)
*/
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length, ans=0;
        int[] up = new int[n];
        int[] down = new int[n];
        for(int i=0;i<n;i++){
            int maxUp=0, maxDown=0;
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i] && down[j]>maxUp){
                    maxUp = down[j];
                } else if(nums[j]>nums[i] && up[j]>maxDown){
                    maxDown = up[j];
                }
            }
            up[i] = 1 + maxUp;
            down[i] = 1 + maxDown;
            ans = Math.max(ans, Math.max(up[i], down[i]));
        }
        return ans;
    }
}

/*
Linear Dp
Time: O(n)
Space: O(n)
up[i] = length of longest wiggle subsequence ending with a positive difference upto i
down[i] = Length of Longest wiggle subsequence ending with a negative difference upto i
These arrays may or may not include ith element.
*/
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length, ans=0;
        if(n==0){
            return 0;
        }
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = down[0] = 1;
        for(int i=1;i<n;i++){
            if(nums[i]<nums[i-1]){
                down[i] = up[i-1]+1;
                up[i] = up[i-1];
            } else if(nums[i]>nums[i-1]){
                up[i] = down[i-1]+1;
                down[i] = down[i-1];
            } else {
                up[i] = up[i-1];
                down[i] = down[i-1];
            }
        }
        return Math.max(up[n-1], down[n-1]);
    }
}

/*
Space optimized linear Dp
Time: O(n)
Space: O(1)
*/
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length, ans=0;
        if(n==0){
            return 0;
        }
        int up=1, down=1;
        for(int i=1;i<n;i++){
            if(nums[i]<nums[i-1]){
                down = up+1;
            } else if(nums[i]>nums[i-1]){
                up = down+1;
            } 
        }
        return Math.max(up, down);
    }
}

/*
Greedy Approach
Time: O(n)
Space: O(1)
*/
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length, ans=0;
        if(n<2){
            return n;
        }
        int prevDiff = nums[1]-nums[0];
        int count = prevDiff==0? 1: 2;
        for(int i=2;i<n;i++){
            int diff = nums[i]-nums[i-1];
            if((diff>0 && prevDiff<=0)||(diff<0 && prevDiff>=0)){
                count++;
                prevDiff = diff;
            }
        }
        return count;
    }
}