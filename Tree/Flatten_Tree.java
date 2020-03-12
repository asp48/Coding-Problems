import java.util.Stack;

/*
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
O(n)
*/
//Recursion - 0ms
class Solution {
    TreeNode prev=null;
    public void flatten(TreeNode root) {
        if(root!=null){
            flatten(root.right);
            flatten(root.left);
            root.right = prev;
            prev=root;
            root.left=null;
        }
    }
}
//Iteration using stack - 1ms
class Solution2 {
    public void flatten(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        while(root!=null){
            if(root.right!=null){
                s.push(root.right);
            }
            root.right = root.left;
            root.left=null;
            if(root.right==null && !s.isEmpty()){
                root.right = s.pop();
            }
            root=root.right;
        }
    }
}
//Iterative with constant space - 0ms
class Solution3 {
    public void flatten(TreeNode root) {
        while(root!=null){
           if(root.left!=null){
               TreeNode tmp = root.left;
               while(tmp.right!=null){
                   tmp = tmp.right;
               }
               tmp.right = root.right;
               root.right = root.left;
               root.left = null;
           }
            root=root.right;
        }
    }
} 