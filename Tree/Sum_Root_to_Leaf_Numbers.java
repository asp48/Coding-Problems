/*
https://leetcode.com/problems/sum-root-to-leaf-numbers/

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
*/

class Solution {
    public int sumNumbers(TreeNode root) {
           return getSum(root, 0);
    }
    
    public int getSum(TreeNode root, int upperSum){
        if(root==null){
            return 0;
        }
        upperSum = 10*upperSum + root.val;
        // return current value if this is leaf node
        if(root.left == null && root.right == null){
            return upperSum;
        }
        // return the decimal sum on left + decimal sum on right
        return getSum(root.left, upperSum) + getSum(root.right, upperSum);
    }
}