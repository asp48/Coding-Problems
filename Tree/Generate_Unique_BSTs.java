import java.util.*;
/*
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

class TreeNode { 
    int val; 
    TreeNode left; 
    TreeNode right; 
    TreeNode(int x) { val = x; } 
}

/*
The concept is same as finding number of unique binary trees for given number of nodes.
Loop for each node as root
    get all trees possible on the left side
    get all trees possible on the right side
    for each left tree
        for each right tree
            create a new node for root
            attach left tree to the left and right tree to the right
            add the node to the result list
Memorization can be applied by using a table to store the computed lists where key is Pair(start,end).
*/
class Solution {
    public List<TreeNode> generateTrees(int n) {
         if(n==0) return new LinkedList<>();
         return getTrees(1,n); 
    }
    
    public List<TreeNode> getTrees(int start, int end){
        List<TreeNode> trees = new LinkedList<>();
        
        if(start>end){
            trees.add(null);
            return trees;
        }
        
        for(int i=start;i<=end;i++){
            List<TreeNode> ltrees = getTrees(start,i-1);
            List<TreeNode> rtrees = getTrees(i+1, end);
            for(TreeNode l: ltrees){
                for(TreeNode r: rtrees){
                    TreeNode t = new TreeNode(i);
                    t.left=l;   
                    t.right=r;
                    trees.add(t);
                }
            }
        }
        return trees;
    }
    
}