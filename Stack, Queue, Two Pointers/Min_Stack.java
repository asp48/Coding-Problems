/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
 

Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
*/

/*
Use a Singly Linked List internally to implement a stack.
When values are pushed to the stack, they are internally added as new nodes at the front of the list.
When values are pooped from the stack, nodes are internally removed from the front of the list.
Store the min element along with each node, such that at any time, node.min gives the minimum value from that node till the end of the list. So when getMin is called on the stack, we just have to return head.min

All operations work in constant time.
*/
class Node {
    Node next;
    int min;
    int val;
    
    public Node(int val){
        this.val = val;
        this.min = val;
    }
}
class MinStack {
    
    Node top;
    
    /** initialize your data structure here. */
    public MinStack() {
        top = null;
    }
    
    public void push(int x) {
        Node node = new Node(x);
        if(top==null){
            top = node;
        } else {
            // update the node.min
            node.min = Math.min(top.min, x);
            // add the new node at the front of the list
            node.next = top;
            top = node;
        }
    }
    
    public void pop() {
        // remove the element from the front
        top = top.next;
    }
    
    public int top() {
        return top.val;
    }
    
    public int getMin() {
        return top.min;
    }
}