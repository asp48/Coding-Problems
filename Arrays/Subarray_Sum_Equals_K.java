import java.util.*;

/*
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/
/*
time: O(n^2), space: O(1)
211 ms, faster than 16.31%
It is a brute force method of checking all subarrays.
*/
class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum=0, count=0;
        int n = nums.length;
        for(int i=0;i<n;i++){
            sum=0;
            for(int j=i;j<n;j++){
                sum+=nums[j];
                if(sum==k){
                    count++;
                }
            }
        }  
        return count;
    }
}
/*
Intuition:
If we know sum[0, i-1] and sum[0, j] then sum[i,j] = sum[0, j] - sum[0, i-1]
In other words, if we know cumulative-sum sum[i-1] and sum[j], then their difference will be equal to sum of all elements from i to j
So a k-sum-subarray ends at j if and only if there exists an index i such that i<j and sum[i] = sum[j]-k. in that case, subarray from i+1 to j form a required k-sum-subarray. 
Therefore, number of k-sum-subarrays ending at j = number of indices i1,i2...upto j-1 whose cumulative sum = sum[j]-k

Logic:
Store each cumulative sum and its number of occurences in a hashmap
While Iterating, if curSum-k exists in hashmap, then count += map.get(curSum-k)
*/
class Solution2 {
    public int subarraySum(int[] nums, int k) {
        int sum=0, count=0, n=nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        // Corner case where sum-k == 0, subarrays starting from index 0
        map.put(0,1);
        for(int i=0;i<n;i++){
            sum+=nums[i];
            if(map.containsKey(sum-k)){
                count+= map.get(sum-k);
            }
            // Update the map after incrementing count to avoid cases wherein k=0 => sum-k=sum
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return count;
    }
}
