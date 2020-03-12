/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

Use two variables to track left and right product. Keep the product in answer array. Left and Right traversal can be combined into a single for loop as below.
*/  
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int leftProduct=1;
        int rightProduct=1;
        for(int i=0;i<n;i++){
            ans[i]=1;
        }
        for(int i=0;i<n;i++) {
            ans[i]*=leftProduct;
            leftProduct *= nums[i];
            ans[n-i-1]*=rightProduct;
            rightProduct*=nums[n-i-1];
        }
        return ans;
    }
}