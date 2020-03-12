import java.util.*;

/*
left-right-root
*/

//recursion
class Solution1 {
    public void helper(TreeNode root, List < Integer > res) {
        if (root != null) {
            helper(root.left, res);
            helper(root.right, res);
            res.add(root.val);
        }
    }
}
/*
Using two stacks.
*/
class Solution2 {
    public List<Integer> postorderIterative(TreeNode root)
	{
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		// create another stack to store post-order traversal
		Stack<Integer> out = new Stack<>();

		while (!stack.empty())
		{
			root = stack.pop();
			out.push(root.val);

			if (root.left != null) {
				stack.push(root.left);
			}

			if (root.right != null) {
				stack.push(root.right);
			}
		}

		List<Integer> result = new ArrayList<>();
		while (!out.empty()) {
			result.add(out.pop());
        }
        return result;
	}
}
/*
Using single stack
*/
class Solution3 {
    public List<Integer> postOrderIterative(TreeNode root) { 
        Stack<TreeNode> stack = new Stack<>(); 
        List<Integer> result = new ArrayList<>();
        while(root!=null || !stack.isEmpty()) { 
            while(root != null) { 
                stack.push(root); 
                stack.push(root); 
                root = root.left; 
            } 
            
            root = stack.pop(); 
              
            if(!stack.empty() && stack.peek() == root) {
                root = root.right; 
            } else { 
                result.add(root.val); 
                root = null; 
            } 
        }
        return result; 
    }
}