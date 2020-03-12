import java.util.*;

import javafx.util.Pair;

/*
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
*/

//Recursive
class Solution1 {
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }   
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

//Iterative
//BFS - 1 ms, faster than 16.13% 
class Solution {
    public int maxDepth(TreeNode root) {
        int level = 0;
        if(root==null) return level;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) { 
            int size = q.size();
            while(size-- > 0) { 
                root=q.poll();
                if(root.left != null){
                    q.add(root.left);
                }
                if(root.right != null){
                    q.add(root.right);
                }
            }
            level++;
        } 
        return level;
    }
}
//DFS-Using Two Stacks - 3 ms, faster than 5.25%
class Solution3 {
    public int maxDepth(TreeNode root) {
        int maxLevel = 0;
        if(root==null) return 0;
        Stack<Pair<TreeNode,Integer>> s = new Stack<>();
        s.add(new Pair<>(root,1));
        while(!s.isEmpty()) { 
            Pair<TreeNode, Integer> p=s.pop();
            root = p.getKey();
            maxLevel = Math.max(maxLevel, p.getValue());
            if(root.left!=null){
                s.push(new Pair<>(root.left, p.getValue()+1));
            }
            if(root.right!=null){
                s.push(new Pair<>(root.right, p.getValue()+1));
            }
        } 
        return maxLevel;
    }
}