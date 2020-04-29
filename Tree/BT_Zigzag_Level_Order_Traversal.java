/*
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/*
Linked list is the key. In a LinkedList, we can add elements to the front in O(1) time.
This will avoid unnecessary reversing of the list of children.
Time: O(N), Space: O((N+1)/2) = O(N)
*/
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if(null == root){
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        while(q.size()>0){
            LinkedList<Integer> list = new LinkedList<>();
            int m = q.size();
            for(int i=0;i<m;i++){
                TreeNode node = q.poll();
                if(node.left!=null) q.add(node.left);
                if(node.right!=null) q.add(node.right);
                if(level%2==0){
                    list.add(node.val);
                } else{
                    list.addFirst(node.val);
                }
            }
            result.add(list);
            level++;
        }
        return result;
    }
}