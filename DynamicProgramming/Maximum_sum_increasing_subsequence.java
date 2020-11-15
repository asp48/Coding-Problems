/*
https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
Given an array arr of N positive integers, the task is to find the maximum sum increasing subsequence of the given array.

Example 1:

Input: N = 5, arr[] = {1, 101, 2, 3, 100} 
Output: 106
Explanation:The maximum sum of a
increasing sequence is obtained from
{1, 2, 3, 100}
Example 2:

Input: N = 3, arr[] = {1, 2, 3}
Output: 6
Explanation:The maximum sum of a
increasing sequence is obtained from
{1, 2, 3}
*/

/*
Similar to Longest Increasing Subsequence. Instead of length, here we have to find sum;
Time: O(N^2)
Space: O(N)
*/
class Solution
{
	public int maxSumIS(int arr[], int n)  
	{  
	    int ans = dp(arr, n);
	    return ans;
	}  
	
	public int dp(int[] arr, int n){
	    int[] dp = new int[n];
	    int ans = 0;
	    for(int i=0;i<n;i++){
	        int max = 0;
	        for(int j=0;j<i;j++){
	            if(arr[i]>arr[j]){
	                max = Math.max(max, dp[j]);
	            }
	        }
	        dp[i] = arr[i] + max;
	        ans = Math.max(ans, dp[i]);
	    }
	    return ans;
	}
}