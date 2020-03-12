import java.util.*;

/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
All the below solution hava time complexity O(n)
Though this problem looks like BFS, DFS solution performs better.
In DFS, we just put the node in the correct level list without waiting for each level list to complete as in BFS.
*/
//Iterative BFS Solution - 1 ms, better than 77% in leetcode
class Solution1 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> lists = new ArrayList<>();
        q.add(root);
        while(!q.isEmpty()){
            int levelSize = q.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0;i<levelSize;i++){
                root = q.poll();
                if(root!=null){
                    level.add(root.val);
                    q.add(root.left);
                    q.add(root.right);
                }
            }
            if(!level.isEmpty()) {
                lists.add(level);
            }
        }
        return lists;
    }
}
//Recursive BFS Solution - 1ms, better than 77% in leetcode
class Solution2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        return helper(list, new ArrayList<>());
    }
    
    public List<List<Integer>> helper(List<TreeNode> processList, List<List<Integer>> result){
        if(processList.isEmpty()) {
            return result;
        }
        int levelSize = processList.size();
        List<Integer> level = new ArrayList<>();
        for(int i=0;i<levelSize;i++){
            TreeNode n = processList.get(0);
            processList.remove(0);
            if(n!=null){
                level.add(n.val);
                processList.add(n.left);
                processList.add(n.right);
            }
        }
        if(!level.isEmpty()) {
            result.add(level);
        }
        return helper(processList, result);
    }
}
//Recursive DFS Solution - 0ms, better than 100% in leetcode
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        return helper(root, 1, new ArrayList<>());
    }
    
    public List<List<Integer>> helper(TreeNode root, int level, List<List<Integer>> result){
        if(root==null) {
            return result;
        }
        List<Integer> list;
        if(result.size()>=level){
            list = result.get(level-1);
            list.add(root.val);
        }else{
            list = new ArrayList<>();
            list.add(root.val);
            result.add(list);
        }
        helper(root.left,level+1,result);
        helper(root.right,level+1,result);
        return result;
    }
}