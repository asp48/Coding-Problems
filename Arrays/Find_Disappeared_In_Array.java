import java.util.*;

/*
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
Same logic as First Missing Positve. Here we have to return all the missing positives.
time: O(n)
space: O(1), result is not considered as extra space
*/
// cyclic sort
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        int i = 0;
        while(i<n){
            if(nums[i]!=nums[nums[i]-1]){
                int t = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = t;
            } else{
                i++;
            }
        }
        List<Integer> result = new ArrayList<>();
        for(i=0;i<n;i++){
            if(i!=nums[i]-1){
                result.add(i+1);
            }
        }
        return result;
    }
}
// marking visited numbers concept
class Solution2 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        int i = 0;
        for(i=0;i<n;i++){
            int current = Math.abs(nums[i]);
            if(nums[current-1]>0){
                nums[current-1] = -nums[current-1];
            }
        }
        List<Integer> result = new ArrayList<>();
        for(i=0;i<n;i++){
            if(nums[i]>0){
                result.add(i+1);
            }
        }
        return result;
    }
}