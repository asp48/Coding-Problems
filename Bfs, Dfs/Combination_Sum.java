/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/

class Solution {
    List<List<Integer>> result = null;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        helper(candidates, target, new ArrayList<>(), 0, 0);
        return result;
    }
    
    public void helper(int[] nums, int target, List<Integer> curList, int curSum, int curIndex){
        // Base condition: Reached target
        if(curSum==target){
            result.add(new ArrayList<>(curList));
            return;
        }
        int sum = curSum;
        // start adding elements from curIndex
        for(int i=curIndex;i<nums.length;i++){
            sum += nums[i];
            // Since array contains only positive integers, it is of no use to recurse further if sum>target.
            if(sum <= target){
                // add current element to the list
                curList.add(nums[i]);
                // recurse further, including the current index because element can be repeated any number of times as per the problem.
                helper(nums, target, curList, sum, i);
                // Remove the current element from the list to backtrack
                curList.remove(curList.size()-1);
            }
            // reset sum to origial state, for backtracking with a different number
            sum = curSum;
        }
    }
}