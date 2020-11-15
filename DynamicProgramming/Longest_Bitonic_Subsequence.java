/*
https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1

Given an array of positive integers. Find the maximum length of Bitonic subsequence. 
A subsequence of array is called Bitonic if it is first increasing, then decreasing.
 

Example 1:

Input: nums = [1, 2, 5, 3, 2]
Output: 5
Explanation: The sequence {1, 2, 5} is
increasing and the sequence {3, 2} is 
decreasing so merging both we will get 
length 5.

Example 2:

Input: nums = [1, 11, 2, 10, 4, 5, 2, 1]
Output: 6
Explanation: The bitonic sequence 
{1, 2, 10, 4, 2, 1} has length 6.
*/

/*
We can use Longest Increasing Subsequence solution to find length of longest increasing subsequence
from 0 upto i. Then using similar approach we can find length of longest decreasing subsequence from
the end of the given array till i. 
ans = max(lis[i]+lds[i]-1) for i in range(0, n);

Time: O(N^2)
Space: O(N)
*/
class Solution
{
    public int LongestBitonicSequence(int[] nums)
    {
        int n = nums.length;
        int[] lis = new int[n];
        /* Longest Increasing Subsequence from 0 to i */
        for(int i=0;i<n;i++){
            int max = 0;
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j] && lis[j]>max){
                    max = lis[j];
                }
            }
            lis[i] = max + 1;
        }
        
        int[] lds = new int[n];
        /* Longest Decreasing Subsequence from i to n-1 */
        for(int i=n-1;i>=0;i--){
            int max = 0;
            for(int j=n-1;j>i;j--){
                if(nums[i]>nums[j] && lds[j]>max){
                    max = lds[j];
                }
            }
            lds[i] = max + 1;
        }
        /* Longest Bitonic Subsequence */
        int ans = 0;
        for(int i=0;i<n;i++){
            ans = Math.max(lis[i]+lds[i]-1, ans);
        }
        return ans;
    }
}