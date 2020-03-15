/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/

/*
Intuition:
In the example [1,2,3], if we know all the permutations of [2,3], i.e [2,3] and [3,2]
we can generate permutations with 1, by inserting 1 at different positions possible. i.e i in the range [0, list.size] inclusively.
Inserting at index 0,
[1,2,3], [1,3,2]
Inserting at index 1,
[2,1,3], [3,1,2]
Inserting at index 2,
[2,3,1], [3,2,1]

Time: O(n!)
Space: O(n) (considering function call stack)
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        return helper(nums, 0);
    }
    
    private List<List<Integer>> helper(int[] nums, int i){
        List<List<Integer>> result = new ArrayList<>();
        // Base condition: permutation of single element
        if(i==nums.length-1){
            result.add(Collections.singletonList(nums[i]));
            return result;
        }
        // get list of all permutations for elements from i+1 to n
        List<List<Integer>> permLists = helper(nums, i+1);
        // for each permutation
        for(List<Integer> plist: permLists){
            // for each position where we can insert the current element
            for(int j=0;j<=plist.size();j++){
                // clone the permutation list
                List<Integer> clonedList = new ArrayList<>(plist);
                // insert the current element
                clonedList.add(j, nums[i]);
                // append the list to the result
                result.add(clonedList);
            }
        }
        return result;
    }
}