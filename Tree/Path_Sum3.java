/*
https://leetcode.com/problems/path-sum-iii/

You are given a binary tree in which each node contains an integer value.
Find the number of paths that sum to a given value.
The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1
Return 3. The paths that sum to 8 are:
1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
*/

/*
The problem is similar to given an array, find number of sub-arrays with sum equals to k.
Already solved here: ../Arrays/Subarray_Sum_Equals_K.java

While traversing down the root, store each unique sum in a hashmap with frequency.
At some node, if curSum-targetSum exists in the map, then its frequency is basically number of subarrays ending at current node having path sum equal to k. 
Corner case:
The problem states that path should always go downwards. For this constraint to be valid in our solution, the frequency of a sum in one subtree should not be used in the other. Therefore, we have to reduce the frequency count back to original after a subtree is completely traversed.
*/
class Solution {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        // for paths starting at root
        map.put(0, 1);
        return helper(root, 0, sum, map);
    }
    
    private int helper(TreeNode root, int curSum, int targetSum, Map<Integer, Integer> map){
        if(root == null){
            return 0;
        }
        // Update the curSum
        curSum += root.val;
        // initialize the count with the frequency of (curSum - targetSum)
        int count = map.getOrDefault(curSum-targetSum, 0);
        /* 
        * Increment the frequency for the curSum. 
        * This should be done only after initializing count, to avoid cases where targetSum=0 => curSum-targetSum = curSum
        */
        map.put(curSum, map.getOrDefault(curSum, 0)+1);
       
        // recurse on the left subtree
        count += helper(root.left, curSum , targetSum, map);
        // recurse on the right subtree
        count += helper(root.right, curSum , targetSum, map);

        /*
        * Reduce the frequency count back to orginal. This is because of the corner case explained above.
        */
        map.put(curSum, map.get(curSum)-1);

        return count;
    }
}