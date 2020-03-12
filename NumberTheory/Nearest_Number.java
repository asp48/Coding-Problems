/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

Solution: O(2*n)
https://leetcode.com/problems/next-permutation/solution/

No solution exists if array is sorted in descending order.
iterate from right until we find a[i] > a[i-1].
    elements towards right of i-1, are already in decreasing order.
    Iterate from end to find element which is nearest to a[i-1]. swap them.
    reverse the elements from index i, to sort them in increasing order.
*/

class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length-1;
        while(i>0 && nums[i] <= nums[i-1]){
            i--;
        }
        if (i > 0) {
            int j = nums.length-1;
            while(nums[i-1] >= nums[j]){
                j--;
            }
            swap(nums, i-1, j);
        }
        reverse(nums, i);
    }
    
    public void reverse(int[] nums, int start){
        int i=start, j=nums.length-1;
        while(i<j){
            swap(nums, i,j);
            i++;
            j--;
        }
    }
    
    public void swap(int[] nums, int i, int j)
    {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}