import java.util.*;

/*
Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
*/
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
//Recursion Top-Bottom- time O(n), space O(depth of tree)
class Solution {
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }
    
    public void invert(TreeNode root){
        if(root==null){
            return;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invert(root.left);
        invert(root.right);
    }
}
//Recursion  Bottom-Up - time O(n), space O(depth of tree)
class Solution2 {
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return root;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
//Iterative using stack, DFS - time O(n), space O(depth of tree)
class Solution3 {
    public TreeNode invertTree(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode head = root;
        while(root!=null || !s.isEmpty()){
            if(root==null){
                root=s.pop();
            }
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            if(root.right!=null){
                s.push(root.right);
            }
            root=root.left;
        }
        return head;
    }
}
//Iterative using queue, BFS - time O(n), space O(number of leaves)
class Solution4 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return root;
    }
}