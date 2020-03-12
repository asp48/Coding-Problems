/*
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

This is a modified version of kadane's algorithm. The challenge here is that when two negative numbers are multiplied, the product is positive and can contribute to global max. This was not the case with addition, wherein two negative numbers will become more negative and hence do not contribute to global max.
In max_sum problem
    local_cur_sum = max(array[i], local_cur_sum + array[i])
Here the decision is to whether add the current number to the contiguous_sum or not. If added, subarray continues, otherwise new subarray starts from index i.
In case of max_product, 
    local_cur_product = max(array[i], local_cur_product*array[i], array[i]*min_so_far)
Here the decision is to whether multiply the current number to contiguous max series or contiguous min series, along with choosing the current number itself. Multiplying min series with a negative number can maximize the product and similarly multiplying max series with a positive number also maximizes the product. Sometimes, it may be necessary to choose the current number itself, which indicates starting of a new max or min series.

time: O(n)
space: O(1)
*/
class Solution {
    public int maxProduct(int[] nums) {
        int cur_max=1;
        int cur_min=1;
        int ans=0;
        for(int num: nums){
            if(num>=0){
                cur_max = Math.max(num, num*cur_max);
                cur_min = Math.min(num, num*cur_min);
            }else{
                int tmp = cur_max;
                cur_max = Math.max(num, num*cur_min);
                cur_min = Math.min(num, num*tmp);
            }
            
            if(ans==0 || cur_max>ans){
                ans=cur_max;
            }
        }
        return ans;
    }
}
//Compact Code of the same above solution
class Solution2 {
    public int maxProduct(int[] nums) {
        int cur_max=1;
        int cur_min=1;
        int ans=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            int tmp = cur_max;
            cur_max = max(nums[i], nums[i]*cur_max, nums[i]*cur_min);
            cur_min = min(nums[i], nums[i]*tmp, nums[i]*cur_min);
            if(cur_max>ans){
                ans=cur_max;
            }
        }
        return ans;
    }
    
    public int max(int a, int b, int c){
        return (a>b)?((a>c)?a:c):((b>c)?b:c);
    }
    
     public int min(int a, int b, int c){
        return (a<b)?((a<c)?a:c):((b<c)?b:c);
    }
}