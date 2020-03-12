import java.util.*;

/*
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

//Recursive
class Solution1 {
    public void helper(TreeNode root, List < Integer > res) {
        if (root != null) {
            helper(root.left, res);
            res.add(root.val);
            helper(root.right, res);
        }
    }
}
//Iterative using stack
class Solution2 {
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}
//Morris traversal, O(n) and No extra space
/*
https://www.educative.io/edpresso/what-is-morris-traversal

Algo:
Let currNode = rootNode
while currNode is not null
    if current node has no left child
        print the current node
        move to the right. currNode = currNode.right
    else
        Let tempNode = currNode.left
        Find rightMostNode in the subtree under tempNode until tempNode.right==NULL or tempNode.right==currNode 
        if there is a loop i.e tempNode.right==currNode
            fix the link by setting tempNode.right=NULL. This implies left tree has been processed.
            Print the current node.
            Move to the right of current node. currNode = currNode.right
        else
            create a link from the rightmost node to currNode to set the return point. This is similar to pop in stack
            i.e tempNode.right = currNode
            Move to the left of the current node. currNode = currNode.left

Idea is to create links as paths to return back to the parent node when a subtree processing is completed. This helps in recalling similar to stacks or function calls in recursion. However, the links should be removed after returning back, in order to retain the structure of input tree.
This data structure is also called threaded binary tree.
*/
class Solution3 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if(root==null){
            return list;
        }
        TreeNode curr=root;
        while(curr!=null){
            System.out.println(curr.val);
            if(curr.left==null){
                System.out.println("No Left Child. Adding " + curr.val + " to result.");
                list.add(curr.val);
                curr = curr.right;
            } else{
                TreeNode temp = curr.left;
                while(temp.right!=null && temp.right!=curr){
                    temp=temp.right;
                }
                if(temp.right==curr){
                    System.out.println("Fixing Link between " + temp.val + " to " + curr.val);
                    list.add(curr.val);
                    System.out.println("Added " + curr.val + " to result.");
                    temp.right=null;
                    curr = curr.right;
                } else{
                    System.out.println("Linking " + temp.val + " to " + curr.val);
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
2
Linking 5 to 2
3
Linking 4 to 3
4
No Left Child. Adding 4 to result.
3
Fixing Link between 4 to 3
Added 3 to result.
5
No Left Child. Adding 5 to result.
2
Fixing Link between 5 to 2
Added 2 to result.
1
Fixing Link between 2 to 1
Added 1 to result.
7
Linking 10 to 7
10
No Left Child. Adding 10 to result.
7
Fixing Link between 10 to 7
Added 7 to result.
12
No Left Child. Adding 12 to result.
[4, 3, 5, 2, 1, 10, 7, 12]
*/