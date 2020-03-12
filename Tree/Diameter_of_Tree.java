/*
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.

Intuition:
At each node, decision is to whether,
1. exclude path passing through root. Take 1 + max depth on left or right subtree. Here root is only either at the start or end of the path.
2. consider path passing through root. Here root is somewhere in the middle of the path
   Take Maximum of 
        2.1   2 + max_depth of left subtree + max_depth of right subtree
        2.2   diameter of left subtree passing through root.left
        2.2   diameter of right subtree passing through root.right

*/
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] res = helper(root);
        int diameter = Math.max(res[0], res[1]);
        return diameter<0 ? 0 : diameter;
    }
    
    public int[] helper(TreeNode root){
        int[] res = new int[2];
        if(root==null){
            // negative value to indicate null node.
            res[0] = -1;
            res[1] = -1;
            return res;
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);

        // Without passing through root, 1 + max-depth on left or right subtree
        int temp = Math.max(left[0], right[0]);
        // Corner case to filter node without any children. Question is about number of edges and minimum two nodes are required to form an edge. temp will be negative for leaf nodes and in such cases we have to return 0.
        res[0] = temp<0? 0: 1 + temp;

        // Passing through root, 
        // max(2 + max-depth on left + max_depth on right,  diameter of left tree including root, diameter of right tree including root)
        temp = left[0] + right[0];
        int withRoot = temp<0? 0: 2 + temp;
        res[1] = Math.max(withRoot, Math.max(left[1], right[1]));

        return res;
    }
}
/*
If we observe closely, path that passes through root will be longest. However it cannot be gauranteed which root. It may not pass through the main root.
So we can have a global variable to store res[1] and modify the function to return res[0] as return value. Now the function basically returns depth of subtree starting from root.
*/
class Solution2 {
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans=1;
        depth(root);
        // Corner case to return number of edges instead of nodes.
        return ans-1;
    }
    
    public int depth(TreeNode root){
        if(root==null){
            return 0;
        }
        int L = depth(root.left);
        int R = depth(root.right);
        // computing global max by finding max of all local maximas, similar to res[1] in previous approach.
        // Variable ans stores number of nodes in the path, therefore only 1 is added to L+R, instead of 2 in previous approach.
        ans = Math.max(ans, L+R+1);
        // returning res[0]
        return 1 + Math.max(L,R);
    }
}