/*
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example1:
Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]

Intuition:
Next pointer of left child = right child
Next pointer of right child = left child of parent's sibling 

Time:O(N), N = number of nodes
Space:O(N), recursion stack 
*/
class Solution {
    public Node connect(Node root) {
        helper(root, null);
        return root;
    }
    
    public void helper(Node root, Node sibling){
        if(root==null || (root.left==null && root.right==null)){
            return;
        }
        root.left.next = root.right;
        Node nextForRightNode = sibling != null? sibling.left: null;
        root.right.next = nextForRightNode;
        helper(root.left, root.right);
        helper(root.right, nextForRightNode);
    }
}
// Cleaner version of above solution
class Solution2 {
    public Node connect(Node root) {
        if(root == null)
            return null;
      
        if(root.left != null)
            root.left.next = root.right;
        if(root.right != null)
            root.right.next = root.next != null ? root.next.left : null ;
        connect(root.left);
        connect(root.right);
        return root;
    }
}