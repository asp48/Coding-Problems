/*
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
*/

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int start=0, end=0, n=nums.length, min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
        for(int i=1;i<n;i++){
            if(nums[i]<nums[i-1]){
                for(int j=i;j<n;j++){
                    min = Math.min(nums[j], min);
                }
                int k=0;
                while(nums[k]<=min){
                    k++;
                }
                start=k;
                break;
            }
        }
        
        for(int i=n-2;i>=0;i--){
            if(nums[i]>nums[i+1]){
                for(int j=i;j>=0;j--){
                    max = Math.max(nums[j], max);
                }
                int k=nums.length-1;
                while(nums[k]>=max){
                    k--;
                }
                end=k;
                break;
            }
        }
        
        return end-start==0? 0: end-start+1;
    }
}