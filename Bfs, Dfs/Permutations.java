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

/*
In the previous solution, insertion and cloning lists every time can slow down the performance. 
To avoid cloning multiple times, we can backtrack to update the same list.

Approach 2: Using backtracking.
Recursion call looks as below.
      ------------------  
     /        |          \
    /         |           \
   1          2            3
  / \        / \          / \
 2   3      1   3        1   2
 |   |      |   |        |   |
 3   2      3   1        2   1
 When we reach the leaf node i.e CurrentPermutationList.size()==nums.length, we append a copy of the list to final result.
 Each path in the above recursion tree contributes to a unique permutation list
*/
class Solution2 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, result, new ArrayList<>(), new boolean[nums.length]);
        return result;
    }
    
    private void helper(int[] nums, List<List<Integer>> result, List<Integer> curList, boolean[] used){
        // Base Condition: Reached leaf node
        if(curList.size() == nums.length){
            // add current list to final result
            result.add(new ArrayList<>(curList));
            return;
        }
        // always start from index 0
        for(int i=0;i<nums.length;i++){
            // skip if element is already used
            if(used[i]){
                continue;
            }
            // add the element to current list
            curList.add(nums[i]);
            // set the current element as used
            used[i] = true;
            // generate all permutations possible with unused elements
            helper(nums, result, curList, used);
            // Remove the current element, to backtrack
            curList.remove(curList.size()-1);
            // set the current element as unused
            used[i] = false;
        }
    }
}

/*
[1,2,3,4]
keeping 1 fixed, recurse on [2,3,4]
    keeping 1,2 fixed, recurse on [3,4]
        keeping 1,2,3 fixed, recurse on [4] // reached end: print 1,2,3,4
        keeping 1,2,4 fixed, recurse on [3] // reached end: print 1,2,4,3
    keeping 1,3 fixed, recurse on [2,4]
        keeping 1,3,2 fixed, recurse on [4] // reached end: print 1,3,2,4
        keeping 1,3,4 fixed, recurse on [2] // reached end: print 1,3,4,2
    keeping 1,4 fixed, recurse on [2,3]
        keeping 1,4,2 fixed, recurse on [3] // reached end: print 1,4,2,3
        keeping 1,4,3 fixed, recurse on [2] // reached end: print 1,4,3,2
keeping 2 fixed, recurse on [1,3,4]
... and so on.
*/
class Solution3 {
    List<List<Integer>> result = null;
    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        helper(nums, 0);
        return result;
    }
    
    private void helper(int[] nums, int start){
        // reached end, add nums to result
        if(start==nums.length-1){
            result.add(convertToList(nums));
            return;
        }
        for(int j=start;j<nums.length;j++){
            // fix nums[j] by swapping it with start index
            swap(nums, start, j);
            // recurse on (start+1, nums.length-1)
            helper(nums, start+1);
            // restore back to original state
            swap(nums, start, j);
        }
    }
    
    // convets array to list
    private List<Integer> convertToList(int[] nums){
        List<Integer> list = new ArrayList<>();
        for(int num: nums){
            list.add(num);
        }
        return list;
    }
    
    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}