/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
*/
/*
Concept: Inorder traversal of BST is same as nodes sorted in ascending order.
Implementation:
Do an inorder traversal on the tree and during the traversal check if current value is greater than previous value, otherwise return false.
*/
class Solution {
    Integer prevValue=null;
    public boolean isValidBST(TreeNode root) {
        Boolean r = isValidBSTree(root);
        prevValue=null;
        return r;
    }
    
    public boolean isValidBSTree(TreeNode root){
        if(root!=null){
            if(!isValidBSTree(root.left)) {
                return false;   
            }
            if(prevValue != null && root.val <= prevValue) {
                return false;
            }
            prevValue=root.val;
            if(!isValidBSTree(root.right)){
                return false;
            }
        }
        return true;
    }
}
/*
Checking if each node value is within the range it is supposed to be.
*/
class Solution2 {
    public boolean isValidBST(TreeNode root) {
        return check(root,null,null);
    }
    
    public boolean check(TreeNode root, Integer lower, Integer upper){
        if(root==null){
            return true;
        }
        if(lower!=null && root.val<=lower) return false;
        if(upper!=null && root.val>=upper) return false;
        
        if(!check(root.left, lower, root.val)) return false;
        if(!check(root.right, root.val, upper)) return false;
        
        return true;
    }
}