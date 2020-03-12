/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.

Example1:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

The solution is easy if we store each original node and its corresponding copy node in a hashmap. Later we can iterate over the keys, and associate the pointers of each copy similar to its original node.

The below solution uses constant space without any hashmaps.

Keep the copy next to its original node in the given list.
Then we iterate over the modified list and update the random pointers of the new nodes. It takes only O(1) time to update random pointer for each copy as we know that copy is located next to its original node.
Finally we extract the copy nodes from the modified list, thereby restoring the original linked list.
*/

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {
    public Node copyRandomList(Node head) {
        if(head==null) return null;
        
        Node orgNode=head; //original node
        //Loop which modifies the given list to keep the copy next to its orginal node
        while(orgNode!=null){
            Node copy = new Node(orgNode.val);
            copy.next = orgNode.next;
            orgNode.next = copy;
            orgNode=copy.next;
        }
        
        orgNode=head;
        //Loop which iterates over copy nodes to updates its random pointer.
        while(orgNode !=null){
            if(orgNode.random != null){
                orgNode.next.random = orgNode.random.next; 
            }
           orgNode = orgNode.next.next;
        }
        
        orgNode=head;
        head=null;
        //Loop which separates original and copy list
        while(orgNode !=null){
            Node tmp = orgNode.next.next;
            orgNode.next.next = tmp==null? null: tmp.next;
            if(head == null){
                // head of the copy node
                head=orgNode.next;
            }
            orgNode.next = tmp;
            orgNode = tmp;
        }
        return head;
    }
}