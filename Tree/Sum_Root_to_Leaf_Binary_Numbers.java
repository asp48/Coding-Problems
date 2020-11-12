/*
You are given the root of a binary tree where each node has a value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers. The answer is guaranteed to fit in a 32-bits integer.

Input: root = [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
*/

class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return getSum(root, 0);
    }
    
    public int getSum(TreeNode root, int upperSum){
        if(root==null){
            return 0;
        }
        // shift uppersum to the left by one position. This is similar to multiplying by 10 in decimal sum.
        upperSum = (upperSum << 1) + root.val;
        if(root.left==null && root.right==null){
            return upperSum;
        }
        return getSum(root.left, upperSum) + getSum(root.right, upperSum);
    }
}