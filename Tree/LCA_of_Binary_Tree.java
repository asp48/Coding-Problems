import java.util.*;

/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]


 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 

Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.
*/

/*
Recursion - time O(n), space O(n). Though it is linear time, it is not an optimal solution.
Here, the assumption is that both p and q are present in the tree. If one of them is not present, in such case, valid result should be null, but this solution returns a pointer to the node which is present in the tree. This can be fixed by using two flags to denote the presence of p and q. If both the flags are set, then return the result, otherwise return null.
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root == p || root==q ){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left!=null && right!=null){
            return root;
        } 
        return left!=null?left:right;
    }
}
/*
Intuition

If we have parent pointers for each node we can traverse back from p and q to get their ancestors. The first common node we get during this traversal would be the LCA node. We can save the parent pointers in a dictionary as we traverse the tree.

Algorithm

Start from the root node and traverse the tree.
Until we find p and q both, keep storing the parent pointers in a dictionary.
Once we have found both p and q, we get all the ancestors for p using the parent dictionary and add to a set called ancestors.
Similarly, we traverse through ancestors for node q. If the ancestor is present in the ancestors set for p, this means this is the first ancestor common between p and q (while traversing upwards) and hence this is the LCA node.

time: O(n), space: O(n)
The below solution seems optimal but was taking more time than the previous. Don't Know Why?
*/
class Solution2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> s = new Stack<>();
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        s.push(root);
        parentMap.put(root, null);
        while(!parentMap.containsKey(p) || !parentMap.containsKey(q)){
            root = s.pop();
            if(root.left!=null){
                parentMap.put(root.left, root);
                s.push(root.left);
            }
            if(root.right!=null){
                parentMap.put(root.right, root);
                s.push(root.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<>();
        while(p!=null){
            ancestors.add(p);
            p = parentMap.get(p);
        }
        while(!ancestors.contains(q)){
            q = parentMap.get(q);
        }
        return q;
    }
}
/*
The above solution can be modified to use only stack (for backtracking), without any hashmap (for storing parent pointers) and set (for storing ancestors).In this approach we always have a pointer to the probable LCA and the moment we find both the nodes we return the pointer as the answer.

There is one more solution: Not optimal, but can cover corner cases.
Search node p in the tree and add all the nodes in the path from root to p in a list.
Search node q in the tree and add all the nodes in the path from root to q in a list.
Then compare the lists to find a common node which is LCA.
*/