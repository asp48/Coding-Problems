/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

/*
1 ms, faster than 62.79% 

Time: O(N * 2^N)
Space: O(N * 2^N)
*/
class Solution {
    List<List<Integer>> result = null;
    public List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList<>();
        helper(nums, new ArrayList<>(), 0);
        return result;
    }
    
    private void helper(int[] nums, List<Integer> curList, int curIndex){
        result.add(new ArrayList<>(curList));
        if(curIndex == nums.length){
            return;
        }
        for(int i=curIndex; i<nums.length; i++){
            curList.add(nums[i]);
            helper(nums, curList, i+1);
            curList.remove(curList.size()-1);
        }
    }
}

/*
When generating subsets, at each element, the decision is to whether include the element or not. So each subset can be represented as a binary string of length n where 1 at position i implies array[i] is included in the current subset and 0 implies it is excluded from the current subset.
Since there will be 2^N subsets for an array of length n, we generate binary numbers from 0 to 2*N-1 and produce subsets by mapping each binary number to array.

There is one challenge with generating binary numbers
When we convert integer to binary string, we get sequence upto leftmost 1. If there is no such 1, then a single bit is returned.
Ex:
0 -> 0
1 -> 1
2 -> 10
3 -> 101
7 -> 111
8 -> 1000
For this problem, we need fixed binary string of length n. To achieve that, enough number of 0s should be padded if binary string length < n and right end bits should be dropped if binary string length > n

One approach to solve this challenge is, create a mask bit having 1 at nth position, then OR it with binary string.
Ex: 
Let n=4
10000 | 0 = 10000
10000 | 1 = 10001
10000 | 111 = 10111
10000 | 1000 = 11000 
Now to get fixed string of length of n=4, we take substring(start=1)
Code:
int nthbit = 1<<n;
for(int i=0;i<(int)Math.pow(2,n);i++){
    String bitmask = Integer.toBinaryString(i|nthbit).substring(1);
}

Another approach is to shift iteration limits by using binary numbers which have n+1 bits. Below solution uses this approach.

Time: O(N * 2^N), (2^N) to generate all bitmasks and N to map them to input array
Space: O(N * 2^N) 
*/
class Solution {
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        int start = (int)Math.pow(2,n);
        int end = (int)Math.pow(2, n+1);
        // end - start = 2^N
        for(int i=start;i<end;i++){
            String bitmask = Integer.toBinaryString(i).substring(1);
            List<Integer> subset = new ArrayList<>();
            for(int j=0;j<n;j++){
                if(bitmask.charAt(j)=='1'){
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }
        return result;
    }
}