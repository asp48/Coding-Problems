/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Solution:
Kadane's algorithm with modification
Below solution handles following cases as well
1) all numbers are negative, ans = max_negative_number
2) n-1 numbers are negative and one is zero, ans = 0
*/

class Solution {
    public int maxSubArray(int[] nums) {
      if (nums.length==0) return -1;
      int max=nums[0];
      int cur_sum=nums[0];
      for (int i=1;i<nums.length;i++){
          cur_sum = Math.max(cur_sum+nums[i], nums[i]);
          max = Math.max(cur_sum, max);
      }
      return max;
    }
}

/*
Through Divide and Conquer

Break the given array into blocks until there is a single block.
Base Condition: If single block, max_sum = value of the block
If more than 1 block, left sum = max from middle till low
                      right sum = max from middle till high
                      max_sum = left sum + right sum
Note; Idea is to include middle element

ans = max(left of middle, right of middle, sum including middle)

*/

class Solution2 {
    public int maxSubArray(int[] nums) {
      if (nums.length==0) return -1;
      return max_sum_subarray(nums, 0, nums.length-1);
    }
    
    public int max_sum_subarray(int[] nums, int l, int h){
        if (l==h){
            return nums[l];
        }
        int m = l + ((h-l)/2);
        return max(max_sum_subarray(nums, l, m),
                   max_sum_subarray(nums, m+1, h),
                   maxIncludingMiddle(nums, l, m, h));
    }

    public int maxIncludingMiddle(int[] nums, int l, int m, int h){
        int sum=0;
        int left_max=Integer.MIN_VALUE;
        int right_max=Integer.MIN_VALUE;
        
        for(int i=m;i>=l;i--){
            sum += nums[i];
            if (sum > left_max){
                left_max = sum;
            }
        }
        
        sum=0;
        for(int i=m+1;i<=h;i++){
            sum += nums[i];
            if (sum > right_max){
                right_max = sum;
            }
        }
        
        return left_max + right_max;
    }
    
    public int max(int a, int b, int c){
        return a>b ? ( a>c ? a : c) : (b>c ? b: c);
    }
}