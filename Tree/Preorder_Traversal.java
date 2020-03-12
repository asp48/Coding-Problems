import java.util.*;

/*
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

//Recursive O(n)
class Solution1 {
    public void helper(TreeNode root, List < Integer > res) {
        if (root != null) {
            res.add(root.val);
            helper(root.left, res);
            helper(root.right, res);
        }
    }
}
//Iterative using stack O(n)
class Solution2 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if(root==null){
            return list;
        }
        Stack<TreeNode> s = new Stack<>();
        while(root!=null || !s.isEmpty()){
            while(root!=null){
                list.add(root.val);
                s.push(root);
                root=root.left;
            }
            root=s.pop();
            root=root.right;
        }
        return list;
    }
}
//Morris Solution, O(n) and No extra space
//Refer to Inorder_Traversal.java for algorithm.
class Solution3 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if(root==null){
            return list;
        }
        TreeNode curr=root;
        while(curr!=null){
            System.out.println(curr.val);
            if(curr.left==null){
                System.out.println("Adding " + curr.val + " to result.");
                list.add(curr.val);
                System.out.println("No Left Child. Moving right");
                curr = curr.right;
            } else{
                TreeNode temp = curr.left;
                while(temp.right!=null && temp.right!=curr){
                    temp=temp.right;
                }
                if(temp.right==curr){
                    System.out.println("Fixing Link between " + temp.val + " to " + curr.val);
                    temp.right=null;
                    curr = curr.right;
                } else{
                    System.out.println("Linking " + temp.val + " to " + curr.val);
                    list.add(curr.val);
                    System.out.println("Added " + curr.val + " to result.");
                    temp.right=curr;
                    curr = curr.left;
                }
            }
        }
        return list;
    }
}
/*
For Solution3
Input:
            1
        2       7
    3        10   12 
4       5
Output:
1
Linking 2 to 1
Added 1 to result.
2
Linking 5 to 2
Added 2 to result.
3
Linking 4 to 3
Added 3 to result.
4
Adding 4 to result.
No Left Child. Moving right
3
Fixing Link between 4 to 3
5
Adding 5 to result.
No Left Child. Moving right
2
Fixing Link between 5 to 2
1
Fixing Link between 2 to 1
7
Linking 10 to 7
Added 7 to result.
10
Adding 10 to result.
No Left Child. Moving right
7
Fixing Link between 10 to 7
12
Adding 12 to result.
No Left Child. Moving right
[1, 2, 3, 4, 5, 7, 10, 12]
*/
