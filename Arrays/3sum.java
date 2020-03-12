/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

Solution:
Iterate over array, fixing one element and then finding two elements such that sum is zero.
Two pointers approach on a sorted array helps to find remaining two numbers.

Options for two sum:
1. Iterate by fixing one element, target=sum-fixedElement check if target exists on the left using hashmap.
There can be duplicate pairs. n
2. Sort the array, Iterate by fixing one element, target=sum-fixedElement check if target exists on the left or right using binary search.
There can be duplicate pairs. n*logn + n*logn
3. Sort the array and use two pointers to find the required two numbers.
There can be duplicate pairs. n*logn + n

Print number of pairs having given sum:
store frequency of each element in hashmap
for each element in array:
    t = sum - elem;
    if map.contains(t) count+=frequency;
    if t==elem count--;            pair with same element twice is invalid
ans = count/2;      because counted twice for each pair
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public List<List<Integer>> threeSum(int[] nums) 
    {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int outer = Integer.MAX_VALUE;
        int inner = 0;
        for(int i=0; i < nums.length-2; i++){
            if(outer==nums[i]){ // to avoid duplicates
                continue;
            }
            outer=nums[i];
            int start=i+1;
            int end=nums.length-1;
            int sum=-nums[i];
            while(start<end){
               if (nums[start]+nums[end]==sum){
                   List<Integer> subset = Arrays.asList(nums[i], nums[start], nums[end]);
                   res.add(subset);
                   inner = nums[start];
                   while(inner == nums[start] && start < end) { 
                        ++start;
                   }
                    inner = nums[end];
                    while(inner == nums[end] && start < end){
                        --end;
                   }
               } else if(nums[start]+nums[end]>sum){
                    end--;
               } else {
                    start++;
               }
            }
        }
        return res;
    }
}