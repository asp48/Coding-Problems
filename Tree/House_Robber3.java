/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.

Similar to House Robber 2, at each node in the tree, the decision is whether to,
    1. add the current value to max sum calculated upto its grand children, or
    2. skip the current node and consider max sum upto its children.
*/
class TreeNode { 
    int val; 
    TreeNode left; 
    TreeNode right; 
    TreeNode(int x) { val = x; } 
}
class Solution {
    public int rob(TreeNode root) {
        return helper(root)[0];
    }
    
    public int[] helper(TreeNode root){
        int[] result = new int[2];
        if(root==null){
            return result;
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        // max sum upto grand children
        int prev = left[1] + right[1];
        // store the current children sum at index 1 in order to return as grand children sum to the parent node.
        result[1] = left[0] + right[0];
        // store the max possible sum upto current node at index 0
        result[0] = Math.max(prev+root.val, result[1]);
        return result;
    }
}