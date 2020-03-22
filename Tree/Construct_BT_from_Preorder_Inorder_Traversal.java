/*
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
*/

/*
Using only one of the traversal, it is impossible to construct back the exact same tree. However if we know two traversals, we can construct the original tree.
In pre-order method, order of traversal is root, root.left, root.right
In inorder method, order of traversal is root.left, root, root.right
So first entry in pre-order array is always root.
If we find the position of that root in inorder array, we can identify the left and right subtree of that root. Then we can construct BT by applying the same logic recursively for the subtrees.
Ex: 
[3,9,20,15,7] and [9,3,15,20,7]
Here 3 is the root. Index of 3 in inorder array is 1. 
So [1-2) and [2-5) form left and right subtree in pre-order array.
and [0-1) and [2-5) form left and right subtree in in-order array.
Now, recurse with left pre-order and left-inorder to construct left subtree.
Next, recurse with right pre-order and right-inorder to construct right subtree.
Assign the subtree root nodes to corresponding pointers of the current root node and return the root.

Time: O(N^2), where N is number of nodes in the tree.
Space: O(depth), depth of tree for function stack
*/
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, preorder.length, 0, inorder.length);
    }
    
    /*
    * pi and pj hold subtree range for pre-order array. Here pi is inclusive and pj is exclusive
    * ii and ij hold subtree range for in-order array. Here ii is inclusive and ij is exclusive
    */
    private TreeNode helper(int[] preorder, int[] inorder, int pi, int pj, int ii, int ij){
        // base condition
        if(pi>=pj || ii>=ij){
            return null;
        }
        // get the root for current subtree, which is the first element of pre-order array
        TreeNode root = new TreeNode(preorder[pi]);
        // if there is only one element, then it is a leaf node
        if(pj-pi==1){
            return root;
        }
        // find the index of current root in in-order array
        int breakIndex = -1;
        for(int i = ii; i<ij; i++){
            if(preorder[pi]==inorder[i]){
                breakIndex = i-ii;
                break;
            }
        }
        // recurse on left subtree
        TreeNode left = helper(preorder, inorder, pi+1, pi+breakIndex+1, ii, ii+breakIndex);
        // recurse on right subtree
        TreeNode right = helper(preorder, inorder, pi+breakIndex+1, pj, ii+breakIndex+1, ij);
        // update current root
        root.left = left;
        root.right = right;
        // return root of the current subtree
        return root;
    }
}

/*
It is given that duplicates does not exist, So above solution can be optimized by using hashmap to store inorder elements and find the index of root in O(1) time.

Time: O(N)
Space: O(N + depth), N for hashmap, depth for function stack
*/
class Solution2 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i], i);
        }
        return helper(preorder, map, 0, preorder.length, 0);
    }
    
    private TreeNode helper(int[] preorder, Map<Integer, Integer> inorderMap, int pi, int pj, int ii){
        if(pi>=pj){
            return null;
        }
        TreeNode root = new TreeNode(preorder[pi]);
        if(pj-pi==1){
            return root;
        }
        // get the index of root directly form inorder hashMap
        int breakIndex = inorderMap.getOrDefault(preorder[pi], -1);
        TreeNode left = helper(preorder, inorderMap, pi+1, pi+(breakIndex-ii)+1, ii);
        TreeNode right = helper(preorder, inorderMap, pi+(breakIndex-ii)+1, pj, breakIndex+1);
        root.left = left;
        root.right = right;
        return root;
    }
}