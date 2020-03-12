import java.util.*;

/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
 

But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3
*/
//recursion time O(n), space O(n) where n is the total number of nodes 
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return checkSymmetry(root, root);
    }
    
    public boolean checkSymmetry(TreeNode node1, TreeNode node2){
        if(node1==null && node2==null){
            return true;
        }
        if(node1==null || node2==null){
            return false;
        }
        if(node1.val != node2.val){
            return false;
        }
        return checkSymmetry(node1.left, node2.right)&&checkSymmetry(node1.right,node2.left);
    }
}

/*
Iterative, BFS using queue. time O(n) and space O(n)
Initially, the queue contains root and root. Then the algorithm works similarly to BFS, with some key differences. Each time, two nodes are extracted and their values compared. Then, the right and left children of the two nodes are inserted in the queue in opposite order. The algorithm is done when either the queue is empty, or we detect that the tree is not symmetric (i.e. we pull out two consecutive nodes from the queue that are unequal).
It was slower than solution1. May be depending on the testcases.
*/
class Solution2 {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while(!q.isEmpty()){
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if(t1==null&&t2==null) continue;
            if(t1==null||t2==null) return false;
            if(t1.val!=t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}
//Using stacks, It was slower than solution2.
class Solution3 {
    public boolean isSymmetric(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        s.push(root);
        while(!s.isEmpty()){
            TreeNode t1 = s.pop();
            TreeNode t2 = s.pop();
            if(t1==null&&t2==null) continue;
            if(t1==null||t2==null) return false;
            if(t1.val!=t2.val) return false;
            s.push(t1.left);
            s.push(t2.right);
            s.push(t1.right);
            s.push(t2.left);
        }
        return true;
    }
}