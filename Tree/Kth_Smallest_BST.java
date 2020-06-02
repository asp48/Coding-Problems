/*
https://leetcode.com/problems/kth-smallest-element-in-a-bst/

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Constraints:
The number of elements of the BST is between 1 to 10^4.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
*/
/*
Inorder traversal of a BST gives elements in sorted order. 
If we find kth element in the inorder traversal, that will be the required kth smallest element.
In a balanced tree
Time: O(logN + K) (O(N) worst case when K=N)
logN -> to find 1st smallest element, +K to find Kth smallest
Space: O(logN) (recursion function stack)
*/
class Solution {
    int count = 0;
    int k=1;
    public int kthSmallest(TreeNode root, int k) {
        this.count=0;
        this.k = k;
        int ans = inorder(root);
        return ans;
    }
    
    public int inorder(TreeNode root){
        if(root==null){
            return -1;
        }
        int val = inorder(root.left);
        if(val!=-1) return val;
        count++;
        if(count==k){
            return root.val;
        }
        val = inorder(root.right);
        return val;
    }
}

/*
Iterative Solution
*/
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        int count = 0;
        while(root!=null || !stack.isEmpty()){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            count++;
            if(count==k) return root.val;
            root = root.right;
        }
        return -1;
    }
}

/*
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Insert/delete operation takes O(logN) time in a balanced BST.
So insert/delete + search(kth smallest) would take O(2logN + k) time.
If we use DLL along with tree, we can reduce the time to O(logN + k).
With this approach, each node would have 4 pointers -> left, right, previous and next
Whenever we are inserting/deleting, we have to adjust all these 4 pointers. Also, we should store reference to head of the DLL and keep it in sync.
Later, when we get a search query of finding 4th smallest element, we just have to traverse our DLL to find the kth element from head.
*/